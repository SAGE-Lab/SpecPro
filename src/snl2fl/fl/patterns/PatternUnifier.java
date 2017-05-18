package it.unige.pat2fl.fl.patterns;

import java.text.ParseException;
import java.util.List;

import it.unige.pat2fl.fl.elements.Atom;
import it.unige.pat2fl.fl.elements.BinaryOperator;
import it.unige.pat2fl.fl.elements.Formula;
import it.unige.pat2fl.fl.elements.UnaryOperator;
import it.unige.pat2fl.fl.visitor.FormulaVisitor;
import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Requirement;

/**
 * Created by Simone Vuotto on 24/11/15.
 */
public class PatternUnifier implements FormulaVisitor {

    private Formula formula;
    private Pattern pattern;
    private List<Formula> scopeFormulae, bodyFormulae;


    public Formula unify(Pattern pattern, List<Formula> scopeFormulae, List<Formula> bodyFormulae) {
        this.pattern = pattern;
        this.scopeFormulae = scopeFormulae;
        this.bodyFormulae = bodyFormulae;
        pattern.getFormula().accept(this);
        return formula;
    }

    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        op.getChild().accept(this);
        formula = new UnaryOperator(formula, op.getOperator());
    }

    @Override
    public void visitBinaryOperator(BinaryOperator op) {
        op.getLeftFormula().accept(this);
        Formula leftFormula = formula;
        op.getRightFormula().accept(this);
        Formula rightFormula = formula;
        formula = new BinaryOperator(leftFormula, rightFormula, op.getOperator());
    }

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

    private int findAtom(String name, List<Expression> expressions){
        for(int i = 0; i < expressions.size(); ++i)
            if(expressions.get(i).toString().equals(name))
                return i;
        return -1;
    }
}
