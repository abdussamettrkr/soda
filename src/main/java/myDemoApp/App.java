/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myDemoApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;




public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {  
       


        get("/", (req, res) -> "Hello, World");


        get("/compute",
            (rq, rs) -> {
                Map<String, String> map = new HashMap<String, String>();
                map.put("result", "not computed yet!");
                return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine()
        );

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          sc1.close();
          System.out.println(inputList);
          
          String input2 = req.queryParams("input2").replaceAll("\\s","");                    
          int input2AsInt = Integer.parseInt(input2);



          boolean result = App.search(inputList, input2AsInt,3,2);

          Map<String, Boolean> map = new HashMap<String, Boolean>();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");

        }, new MustacheTemplateEngine());




    }

    public static boolean search(ArrayList <Integer> arr, int n1,int n2,int n3){
        boolean result = false;
        int temp = n2*n3;
        if(arr == null)
            return false;
        
        // check n1 exist
        for (Integer integer : arr) {
            if(integer.equals(n1))
                result = true;
        }

        if(!result)
            return result;
        result  = false;
        // check n2*n3 exists in arr
        for (Integer integer : arr) {
            if(integer.equals(temp))
                result = true;
        }
        
        return result;

    }

    
}
