package snl2fl.fl.patterns;

import java.text.ParseException;
import java.util.List;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.Formula;
import snl2fl.fl.elements.UnaryOperator;
import snl2fl.fl.visitor.FormulaVisitor;
import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;

/**
 * The Class PatternUnifier.
 *
 * @author Simone Vuotto
 */
public class PatternUnifier implements FormulaVisitor {

    /** The formula. */
    private Formula formula;
    
    /** The pattern. */
    private Pattern pattern;
    
    /** The body formulae. */
    private List<Formula> scopeFormulae, bodyFormulae;


    /**
     * Unify.
     *
     * @param pattern the pattern
     * @param scopeFormulae the scope formulae
     * @param bodyFormulae the body formulae
     * @return the formula
     */
    public Formula unify(Pattern pattern, List<Formula> scopeFormulae, List<Formula> bodyFormulae) {
        this.pattern = pattern;
        this.scopeFormulae = scopeFormulae;
        this.bodyFormulae = bodyFormulae;
        pattern.getFormula().accept(this);
        return formula;
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.visitor.FormulaVisitor#visitUnaryOperator(snl2fl.fl.elements.UnaryOperator)
     */
    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        op.getChild().accept(this);
        formula = new UnaryOperator(formula, op.getOperator());
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.visitor.FormulaVisitor#visitBinaryOperator(snl2fl.fl.elements.BinaryOperator)
     */
    @Override
    public void visitBinaryOperator(BinaryOperator op) {
        op.getLeftFormula().accept(this);
        Formula leftFormula = formula;
        op.getRightFormula().accept(this);
        Formula rightFormula = formula;
        formula = new BinaryOperator(leftFormula, rightFormula, op.getOperator());
    }

    /* (non-Javadoc)
     * @see snl2fl.fl.visitor.FormulaVisitor#visitAtom(snl2fl.fl.elements.Atom)
     */
    @Override
    public void visitAtom(Atom at) {
        Requirement r = pattern.getRequirement();
        int scopeIndex = findAtom(at.getName(), r.getScope().getExpressions());
        int bodyIndex  = findAtom(at.getName(), r.getExpressions());
        if(scopeIndex != -1 && bodyIndex != -1)
            throw new RuntimeException("variable name \""+at.getName()+ "\" found twice in the pattern configuration file, impossible to unify");
        if(scopeIndex == -1 && bodyIndex == -1)
            throw new RuntimeException("variable name \""+at.getName()+ "\" not found in the pattern configuration file, impossible to unify");
        if(scopeIndex != -1)
            formula = scopeFormulae.get(scopeIndex);
        if(bodyIndex != -1)
            formula = bodyFormulae.get(bodyIndex);
    }

    /**
     * Find atom.
     *
     * @param name the name
     * @param expressions the expressions
     * @return the int
     */
    private int findAtom(String name, List<Expression> expressions){
        for(int i = 0; i < expressions.size(); ++i)
            if(expressions.get(i).toString().equals(name))
                return i;
        return -1;
    }
}
