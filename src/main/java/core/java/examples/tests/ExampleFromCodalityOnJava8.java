package core.java.examples.tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleFromCodalityOnJava8 {

    /**
     * This example is going to make email addresses for a company personal.
     * It takes personal names in a string each one separated by ";".
     * They may have middle name and their names can contain "-".
     * The resulting email for each personal should be like
     * "firstLetterOfFirstName_familyNameWithoutDash@companyName.com" all in lower case letters.
     * In case of redundant emails, add index 2, 3 and so on to them (the first one does not have 1 at the end).
     * At the end concat all the emails by ";" as a single string.
     * @param args
     */
    public static void main(String[] args) {
        String names = "Hamed Mirzaei; Ali Ace Berge; Jorge W Washing-ton; Holla Bay Mirzaei";
        String companyName = "Example";

        System.out.println("Names are: " + names);
        System.out.println("Company name is: " + companyName);

        Map<String, Integer> nameCount = new HashMap<String, Integer>();

        String result = Arrays.stream(names.replaceAll("-","").toLowerCase().split("; "))
                .map(
                        e -> {
                            if (e.split(" ").length == 3)
                                return e.split(" ")[0].charAt(0) + "_" + e.split(" ")[2];
                            else
                                return e.split(" ")[0].charAt(0) + "_" + e.split(" ")[1];
                })
                .map(e -> {
                    if (nameCount.containsKey(e)) {
                        nameCount.put(e, nameCount.get(e) + 1);
                        return e + nameCount.get(e);
                    } else {
                        nameCount.put(e, 1);
                        return e;
                    }
                })
                .map(e -> e + "@" + companyName.toLowerCase() + ".com")
                .reduce((a,b) -> a + "; " + b)
                .get();

        System.out.println("Result is: " + result);
    }
}
