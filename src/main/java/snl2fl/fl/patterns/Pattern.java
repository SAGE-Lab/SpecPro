package snl2fl.fl.patterns;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import snl2fl.fl.elements.Formula;
import snl2fl.fl.parser.FLBuilder;
import snl2fl.fl.parser.FLGrammarLexer;
import snl2fl.fl.parser.FLGrammarParser;
import snl2fl.req.parser.RequirementsBuilder;
import snl2fl.req.parser.RequirementsGrammarLexer;
import snl2fl.req.parser.RequirementsGrammarParser;
import snl2fl.req.requirements.Requirement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The Class Pattern.
 *
 * @author Simone Vuotto
 */
public class Pattern {
    
    /** The Constant PATTERNS_FILE. */
    public static final String PATTERNS_FILE = "/patterns_to_ltl.json";
    
    /** The formula. */
    private final Formula formula;
    
    /** The requirement. */
    private final Requirement requirement;

    /**
     * Instantiates a new pattern.
     *
     * @param requirement the requirement
     * @param formula the formula
     */
    public Pattern(Requirement requirement, Formula formula) {
        this.requirement = requirement;
        this.formula = formula;
    }

    /**
     * Gets the formula.
     *
     * @return the formula
     */
    public Formula getFormula() { return formula; }

    /**
     * Gets the requirement.
     *
     * @return the requirement
     */
    public Requirement getRequirement() { return  requirement; }

    /**
     * Load patterns.
     *
     * @param configurationFile the configuration file
     * @return the map
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the JSON exception
     */
    public static Map<String, Pattern> loadPatterns(String configurationFile) throws IOException, JSONException {
    	HashMap<String, Pattern> patterns = new HashMap<>();
        ParseTreeWalker walker = new ParseTreeWalker();

        InputStream is = Pattern.class.getResourceAsStream(configurationFile);

        if(is == null) throw new IOException("Resource not found");

        String json = IOUtils.toString(is);
  
        JSONArray jsonArray = new JSONArray(json);
  
        for(int i=0; i < jsonArray.length(); ++i) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String body = obj.getString("body");
            JSONObject formulae = obj.getJSONObject("formula");
            Iterator keys = formulae.keys();
            while(keys.hasNext()) {
                String scope = (String) keys.next();
                String textRequirement = scope + ", "+body;
                //Parse the requirement
                RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(CharStreams.fromString(textRequirement));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);


                RequirementsBuilder builder = new RequirementsBuilder();
                walker.walk(builder, parser.file());
                List<Requirement> requirements = builder.getRequirementList();
                Requirement requirement = requirements.get(0);

                // Parse the formula
                String textFormula = formulae.getString(scope);
                FLGrammarLexer flLexer = new FLGrammarLexer(CharStreams.fromString(textFormula));
                CommonTokenStream flTokens = new CommonTokenStream(flLexer);
                FLGrammarParser flParser = new FLGrammarParser(flTokens);
                FLBuilder flBuilder = new FLBuilder();

                walker.walk(flBuilder, flParser.fl());

                Formula formula = flBuilder.getFormula();
                Pattern p = new Pattern(requirement, formula);
                patterns.put(requirement.key(), p);                
            }
        }
        
        return patterns;
    }
}
