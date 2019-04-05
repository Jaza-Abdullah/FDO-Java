/*
 Main Paper 
 J. M. Abdullah and T. A. Rashid, "Fitness Dependent Optimizer: Inspired by the Bee Swarming Reproductive Process," in IEEE Access.
doi: 10.1109/ACCESS.2019.2907012
keywords: {Optimization;Heuristic algorithms;Classification algorithms;Genetic algorithms;Artificial bee colony algorithm;Particle swarm optimization;Optimization;Swarm Intelligence;Evolutionary Computation;Metaheuristic Algorithms;Fitness Dependent Optimizer;FDO},
URL: http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=8672851&isnumber=6514899
 */


import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jaza Mahmood Abdullah
 * Apache License Version 2.0, January 2004
 * http://www.apache.org/licenses/
 */
class FDOSingleObjective {
    private String functionName;
    private int maxIteration = 200;
    private int numberOfAgents = 30;
    Functions func;
    private int dimenssion;
    private double lowerBound;
    private double upperBound;
    private double weightFactor = 0.0;
    private int turns = 1;

    ArrayList<Bee> globalBest = new ArrayList<Bee>();
    ArrayList avarageFitness = new ArrayList();
    ArrayList avarageOver50Trails = new ArrayList();
    ArrayList<Double> meanList = new ArrayList<Double>();

    private boolean shouldPrintEveryBee = false;
    private boolean shouldPrintAverageFitnessForEachTurn = false;
    private boolean shouldPrintGlobalBestForEachIteration = false;
    private boolean shouldPrintGlobalBestAverageForAllTurns = false;
    private boolean agentRemainInsideBoundery = true;

    public void setShouldPrintGlobalBestForEachTurn(boolean shouldPrintGlobalBestForEachTurn) {
        this.shouldPrintGlobalBestForEachTurn = shouldPrintGlobalBestForEachTurn;
    }

    private boolean shouldPrintGlobalBestForEachTurn = false;

    public FDOSingleObjective(String functionName) {
        this.functionName = functionName;
        func = new Functions(functionName);
        dimenssion = func.getDimensions();
        lowerBound = func.getLowerBound()[0];
        upperBound = func.getUpperBound()[0];
    }

    public FDOSingleObjective(int maxIteration, int numberOfAgents, String functionName) {
        this.maxIteration = maxIteration;
        numberOfAgents = numberOfAgents;
        this.functionName = functionName;
    }

    public FDOSingleObjective(int maxIteration, int numberOfAgents, String functionName, int dimenssion, double lowerBound, double upperBound) {
        this.maxIteration = maxIteration;
        this.numberOfAgents = numberOfAgents;
        this.functionName = functionName;
        this.dimenssion = dimenssion;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public FDOSingleObjective(int maxIteration, int numberOfAgents, String functionName, int dimenssion, double lowerBound, double upperBound, double weightFactor) {
        this.maxIteration = maxIteration;
        this.numberOfAgents = numberOfAgents;
        this.functionName = functionName;
        this.dimenssion = dimenssion;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.weightFactor = weightFactor;
    }

    public FDOSingleObjective(int maxIteration, int numberOfAgents, String functionName, int dimenssion, double lowerBound, double upperBound, double weightFactor, int turns) {
        this.maxIteration = maxIteration;
        this.numberOfAgents = numberOfAgents;
        this.functionName = functionName;
        this.dimenssion = dimenssion;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.weightFactor = weightFactor;
        this.turns = turns;
    }

    public void runFDO() {
        printBasicInfo();
        int cmain = 0, cpace = 0, cdis = 0, cnon = 0, tryC = 0;
        boolean isCnon = true;
        for (int turn = 0; turn < turns; turn++) {
            avarageFitness.clear();
            globalBest.clear();
            /*
             generate artificial scout bees equal to @param numberOfAgents
             randomly with in @param lowerBound and @param uperBound
             */
            ArrayList<Bee> scouts = new ArrayList<Bee>();
            for (int agent = 0; agent < numberOfAgents; agent++) {
                ArrayList<Double> xis = new ArrayList<Double>();
                for (int j = 0; j < dimenssion; j++) {
                    xis.add(getRandomXY());
                }
                scouts.add(new Bee(xis, agent, func));
            }
            // FOD algorithm Iteration start here
            int iterate = 0;
            for (; iterate < maxIteration; iterate++) {
////////////////////////////           //     printReplaceable("Turn: " + (turn + 1) + "   Iteration: " + (iterate + 1));
                for (Bee bee : scouts) {
                    // get current global best bee
                    ArrayList bestBeeXis = getBestBee(scouts).getXis();
                    ArrayList tempXs = new ArrayList();
                    ArrayList lastPace = new ArrayList();
                    double fitnessWeight = 0.0;
                    if (bee.getBeeFitness(functionName) != 0) {
                        //generate fitness weight fW
                        fitnessWeight = (getBestBee(scouts).getBeeFitness(functionName) / bee.getBeeFitness(functionName)) - weightFactor;
                    }
                    for (int dim = 0; dim < dimenssion; dim++) {
                        double distanceFromBestBee = (double) bestBeeXis.get(dim) - (double) bee.getXis().get(dim);
                        double x = (double) bee.getXis().get(dim);
                        double pace = 0.0;
                        double r = simpleRandomWalk();
                        if (fitnessWeight == 1) {
                            pace = x * r;
                        } else if (fitnessWeight == 0) {
                            pace = distanceFromBestBee * r;
                        } else {
                            pace = (distanceFromBestBee * fitnessWeight);
                            if (r < 0) {
                                pace = pace * -1;
                            }
                        }
                        if (Double.isInfinite(pace)) {
                            pace = x * r;
                        }
                        double newBeeXs = x + pace;
                        if (agentRemainInsideBoundery) {
                            newBeeXs = getIntoBounderyLimit(newBeeXs);
                        }
                        tempXs.add(newBeeXs);
                        lastPace.add(pace); //save pace for potential reuse
                    }
                    // create temporary bee for comparision purpose
                    Bee tempBee = new Bee(tempXs, 0, func);
                    //use < for minimization problem and > for maximization
                    if (tempBee.getBeeFitness(functionName) < bee.getBeeFitness(functionName)) {
                        bee.setXis(tempXs);
                        bee.setLastPast(lastPace);
                        cmain++;
                        isCnon = false;
                    } else if (bee.getLastPace().size() > 0) {
                        tempXs.clear();
                        for (int n = 0; n < dimenssion; n++) {
                            double distanceFromBestBee = (double) bestBeeXis.get(n) - (double) bee.getXis().get(n);
                            double x = (double) bee.getXis().get(n) + (distanceFromBestBee * fitnessWeight) + (double) bee.getLastPace().get(n);
                            if (agentRemainInsideBoundery) {
                                x = getIntoBounderyLimit(x);
                            }
                            tempXs.add(x);
                        }
                        tempBee = new Bee(tempXs, 0, func);
                        //use < for minimization problem and > for maximization
                        if (tempBee.getBeeFitness(functionName) < bee.getBeeFitness(functionName)) {
                            bee.setXis(tempXs);
                            cpace++;
                            isCnon = false;
                        }
                    } else {
                        tempXs.clear();
                        for (int m = 0; m < dimenssion; m++) {
                            for (int n = 0; n < dimenssion; n++) {
                                double r = simpleRandomWalk();
                                double x = (double) bee.getXis().get(n) + (double) bee.getXis().get(n) * r;

                                if (agentRemainInsideBoundery) {
                                    x = getIntoBounderyLimit(x);
                                }
                                tempXs.add(x);
                            }
                        }
                        tempBee = new Bee(tempXs, 0, func);
                        //use < for minimization problem and > for maximization
                        if (tempBee.getBeeFitness(functionName) < bee.getBeeFitness(functionName)) {
                            bee.setXis(tempXs);
                            cdis++;
                            isCnon = false;
                            break;
                        }

                    }
                    if (shouldPrintEveryBee) {
                        printBee(bee);
                    }
                    //   System.out.println("bee number("+bee.getNumber()+") "+bee.getBeeFitness(functionName));
                    if (isCnon) {
                        cnon++;
                    } else {
                        isCnon = true;
                    }
                }//scout iterate end here
                //  System.out.println("best bee number("+getBestBee(scouts).getNumber()+") "+getBestBee(scouts).getBeeFitness(functionName));
                globalBest.add(getBestBee(scouts));
                avarageFitness.add(getAvarageFitness(scouts));

            } //  Iteration End here
            if (shouldPrintAverageFitnessForEachTurn) {
                printFitnessAverage();
            }
            if (shouldPrintGlobalBestForEachIteration) {
                printGlobalBest();
            }
            if (shouldPrintGlobalBestForEachTurn) {
                printGlobalBestForEachTurn(getBestBee(scouts));
            }
            meanList.add(getBestBee(scouts).getBeeFitness(functionName));
            avarageOver50Trails.add(getAvarageFitness(scouts));
        }// turns end here
        System.out.println();
        if (shouldPrintGlobalBestAverageForAllTurns) {
            printAverageGlobalBestForAllTurns();
        }
        double allIteration = turns * maxIteration * numberOfAgents;
        System.out.println("cmain = " + cmain + " cpace= " + cpace + " cdis= " + cdis + " cnon= " + cnon);
        System.out.println("cmain = %" + (((double) cmain / allIteration) * 100) + "\n cpace= %" + (((double) cpace / allIteration) * 100) + "\n cdis= % " + (((double) cdis / allIteration) * 100) + "\n cnon= %" + (((double) cnon / allIteration) * 100));
    }

    private double getIntoBounderyLimit(double newBeeXs) {
        if (newBeeXs > upperBound) {
            newBeeXs = upperBound * new Random().nextDouble();
        } else if (newBeeXs < lowerBound) {
            newBeeXs = lowerBound * new Random().nextDouble();
        }
        return newBeeXs;
    }

    private void printGlobalBestForEachTurn(Bee bestBee) {
////////////////       System.out.println();
        System.out.println( bestBee.getBeeFitness(functionName));
////////////        System.out.println("Global Best : " + bestBee.getBeeFitness(functionName));
    }

    void printReplaceable(String str) {
        System.out.print("\r ");
        System.out.print(str);

    }

    private double getMean() {
        double mean = 0.0;
        for (int j = 0; j < globalBest.size(); j++) {
            mean += globalBest.get(j).getBeeFitness(functionName);
        }
        return (mean / globalBest.size() - 1);
    }

    void printMean() {
        double mean = 0.0;
        for (int j = 0; j < meanList.size(); j++) {
            mean += meanList.get(j);
        }
        System.out.println("Mean = " + mean / meanList.size());
    }

    void printStandardDeviation() {
        double mean = 0.0;
        for (int j = 0; j < meanList.size(); j++) {
            mean += meanList.get(j);
        }
        mean = mean / meanList.size();
        double std = 0.0;
        for (Double s : meanList) {
            std += Math.pow(s - mean, 2);
        }
        std = Math.sqrt(Math.abs(std) / meanList.size());
        System.out.println("Standard Deviation = " + std);
    }

    void printAverageGlobalBestForAllTurns() {
        System.out.println("avarageOver50Trails");
        for (Object d : avarageOver50Trails) {
            double b = (double) d;
            System.out.println(b);
        }
        System.out.println("End -------------------------");
    }

    private void printFitnessAverage() {
        System.out.println("avarageFitness");
        for (Object d : avarageFitness) {
            double b = (double) d;
            System.out.println(b);
        }
        System.out.println("End -------------------------");
    }

    void printGlobalBest() {
        System.out.println("globalBest");
        for (Object d : globalBest) {
            Bee b = (Bee) d;
            System.out.println(b.getBeeFitness(functionName));
        }
        System.out.println("End -------------------------");
    }

    void printBestBee(ArrayList<Bee> scouts) {
        Bee bestBee = getBestBee(scouts);
        System.out.print("Best Bee(" + bestBee.getNumber() + ") ");
        System.out.print(bestBee.getBeeFitness(functionName) + ",");
        for (int j = 0; j < dimenssion; j++) {
            System.out.print("   dim: " + j + "= " + bestBee.getXis().get(j));
        }
    }

    void printBasicInfo() {
        System.out.println("Function Name= " + functionName);
        System.out.println("Number of Scout Bees: " + numberOfAgents);
        System.out.println("Dimension " + dimenssion);
        System.out.println("Upper Boundary limit: " + upperBound);
        System.out.println("Lower Boundary limit: " + lowerBound);
        System.out.println("Iteration: " + maxIteration);
        System.out.println("Turns: " + turns);
        System.out.println("Weight Factor: " + weightFactor);
    }

    void printBee(Bee bee) {
        System.out.print("Bee(" + bee.getNumber() + ") ");
        for (int j = 0; j < dimenssion; j++) {
            System.out.print("   dim- " + j + "= " + bee.getXis().get(j));
        }
        System.out.println();
    }


    public void setShouldPrintEveryBee(boolean shouldPrintEveryBee) {
        this.shouldPrintEveryBee = shouldPrintEveryBee;
    }

    public void setShouldPrintAverageFitnessForEachTurn(boolean shouldPrintAverageFitnessForEachTurn) {
        this.shouldPrintAverageFitnessForEachTurn = shouldPrintAverageFitnessForEachTurn;
    }

    public void setShouldPrintGlobalBestForEachIteration(boolean shouldPrintGlobalBestForEachIteration) {
        this.shouldPrintGlobalBestForEachIteration = shouldPrintGlobalBestForEachIteration;
    }

    public void setShouldPrintGlobalBestAverageForAllTurns(boolean shouldPrintGlobalBestAverageForAllTurns) {
        this.shouldPrintGlobalBestAverageForAllTurns = shouldPrintGlobalBestAverageForAllTurns;
    }

    public void setAgentRemainInsideBoundery(boolean agentRemainInsideBoundery) {
        this.agentRemainInsideBoundery = agentRemainInsideBoundery;
    }

    double getAvarageFitness(ArrayList<Bee> scouts) {
        double fitness = 0.0;
        for (Bee bee : scouts) {
            fitness += bee.getBeeFitness(functionName);
        }

        return (fitness / scouts.size());
    }

    Bee getBestBee(ArrayList<Bee> scouts) {
        Bee bestBee = scouts.get(0); //first bee
        for (Bee bee : scouts) {
            if (bee.getBeeFitness(functionName) < bestBee.getBeeFitness(functionName)) {
                bestBee = bee;
            }
        }
        return bestBee;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public void setNumberOfAgents(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }

    public void setDimenssion(int dimenssion) {
        this.dimenssion = dimenssion;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public void setWeightFactor(double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    private Double getRandomXY() {
        Random r = new Random();
        Double randomValue = lowerBound + (upperBound - lowerBound) * r.nextDouble();
        return randomValue;
    }

    private double simpleRandomWalk() {
        double beta = 0.5;
        double sigma = Math.pow((gamma(1 + beta) * Math.sin((Math.PI * beta) / 2) / (gamma((1 + beta) / 2) * beta * Math.pow(2, (beta - 1) / 2))), (1 / beta));
        double r1 = simpleRandom() * sigma;
        double r2 = simpleRandom();
        double levyFlight = (r1 / Math.pow(Math.abs(r2), 1 / beta)) * 0.01;
        if (levyFlight >= 1.0 || levyFlight <= -1.0) {
            return simpleRandomWalk();
        } else {
            return levyFlight;
        }
    }

    private double simpleRandom() {

        Double randomValue = -1 + (1 - (-1)) * Math.random();
        //  System.out.println(randomValue);
        return 0.1 * randomValue;
    }

    private double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    private double gamma(double x) {
        return Math.exp(logGamma(x));
    }
}
