package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

class RuleParser {

    public RuleParser() {
    }

    public boolean parseFlag(int rule) {
        JSONParser ruleParser = new JSONParser();

        try (FileReader reader = new FileReader("Rules.json")) {

            JSONObject jsonObj = (JSONObject) ruleParser.parse(reader);
            JSONArray ruleList = (JSONArray) jsonObj.get("Rules");

            for (int i = 0; i <= ruleList.size() - 1; i++) {
                JSONObject jsonObj2 = (JSONObject) ruleList.get(i);

                if (rule == (int) (long) jsonObj2.get("rule")) {
                    return (boolean) jsonObj2.get("flag");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
