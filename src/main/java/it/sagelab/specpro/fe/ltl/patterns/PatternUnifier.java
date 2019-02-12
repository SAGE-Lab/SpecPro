package it.sagelab.specpro.fe.ltl.patterns;

import java.util.List;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.models.ltl.FormulaVisitor;
import it.sagelab.specpro.models.psp.expressions.Expression;
import it.sagelab.specpro.models.psp.Requirement;

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
     * @see FormulaVisitor#visitUnaryOperator(UnaryOperator)
     */
    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        op.getChild().accept(this);
        formula = new UnaryOperator(formula, op.getOperator());
    }

    /* (non-Javadoc)
     * @see FormulaVisitor#visitBinaryOperator(BinaryOperator)
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
     * @see FormulaVisitor#visitAtom(Atom)
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
