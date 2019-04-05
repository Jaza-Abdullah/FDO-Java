/**
 * @author Jaza Mahmood Abdullah
 * Apache License Version 2.0, January 2004
 * http://www.apache.org/licenses/

 Main Paper 
 J. M. Abdullah and T. A. Rashid, "Fitness Dependent Optimizer: Inspired by the Bee Swarming Reproductive Process," in IEEE Access.
doi: 10.1109/ACCESS.2019.2907012
keywords: {Optimization;Heuristic algorithms;Classification algorithms;Genetic algorithms;Artificial bee colony algorithm;Particle swarm optimization;Optimization;Swarm Intelligence;Evolutionary Computation;Metaheuristic Algorithms;Fitness Dependent Optimizer;FDO},
URL: http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=8672851&isnumber=6514899
 */




//you can use FX1, FX2 ... FX19,FXantena and function_FM for functionName
public class Main {
    public static void main(String args[]) {
        /*
        there are many form of @constructor FDOSingleObjective to use.
        after creation of FDOSingleObjective constructor, you can set
        these  optional features:
            setShouldPrintEveryBee
            setShouldPrintAverageFitnessForEachTurn
            setShouldPrintGlobalBestForEachIteration
            setShouldPrintGlobalBestAverageForAllTurns

            before running the FDO algorithm you might set these parameters
            using FDOSingleObjective  setter method or you may leave with
            their default values:
                 String functionName = "FX1";
                 int maxIteration = 200;
                 int numberOfAgents = 30;
                 int dimenssion = function_default_value
                 double lowerBound = function_default_value
                 double upperBound = function_default_value
                 double weightFactor = 0.0;
                 int turns = 1;

            then run the algorithm   using @method runFDO()
            after that you can use these method to see the results
                printMean
                printStandardDeviation
                printAverageGlobalBestForManyTurns

         */

        // an example
        // you can use FX1, FX2 ... FX19, function_FM  and function_antenna
        FDOSingleObjective fdo = new FDOSingleObjective("FX19");
        fdo.setWeightFactor(0.0);
        fdo.setShouldPrintGlobalBestForEachTurn(true);
        fdo.setAgentRemainInsideBoundery(false);
        fdo.setNumberOfAgents(30);
        fdo.setMaxIteration(500);
        fdo.setTurns(30);
        fdo.runFDO();
        fdo.printMean();
        fdo.printStandardDeviation();

    }


    String iconeLinkGenerator(int cod){
        String firstPart = "http://openweathermap.org/img/w/";
        if(cod ==200 || cod == 201) return firstPart+"11d.png";
        else if(cod == 301 || cod == 300) return  firstPart+"09d.png";
    }

}
