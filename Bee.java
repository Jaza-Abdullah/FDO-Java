/*
 Main Paper 
 J. M. Abdullah and T. A. Rashid, "Fitness Dependent Optimizer: Inspired by the Bee Swarming Reproductive Process," in IEEE Access.
doi: 10.1109/ACCESS.2019.2907012
keywords: {Optimization;Heuristic algorithms;Classification algorithms;Genetic algorithms;Artificial bee colony algorithm;Particle swarm optimization;Optimization;Swarm Intelligence;Evolutionary Computation;Metaheuristic Algorithms;Fitness Dependent Optimizer;FDO},
URL: http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=8672851&isnumber=6514899
 */

import java.util.ArrayList;

/**
 *
 * @author Solid
 */
public class Bee {

    ArrayList xis = new ArrayList();
    ArrayList lastPast = new ArrayList();
    boolean isDominated = true;
    int number;
    Functions fitness ;

    public boolean isDominated() {
        return isDominated;
    }

    public void setIsDominated(boolean isDominated) {
        this.isDominated = isDominated;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Bee(ArrayList p, int number, Functions fitness ) {
        this.xis.addAll(p);
        this.number = number;
        this.fitness =fitness;
    }

    public ArrayList getXis() {
        return xis;
    }

    public void setXis(ArrayList p) {
        this.xis = p;
    }

    public ArrayList getLastPace() {
        return lastPast;
    }

    public void setLastPast(ArrayList lastPast) {
        this.lastPast = lastPast;
    }

    @Override
    public String toString() {
        String str="";
        for(Object d : this.xis){
            str += (double)d+ ", ";
        }
        str = str.substring(0, str.length()-2);
        return str; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    double getBeeFitness(String functionName) {
        double ft = 0.0;
        switch (functionName) {
            case "function_FM":
                ft = fitness.function_FM(xis);
                break;
            case "function_antenna":
                ft = fitness.function_antenna(xis);
                break;
            case "FX1":
                ft = fitness.function_1(xis);
                break;
            case "FX2":
                ft = fitness.function_2(xis);
                break;
            case "FX3":
                ft = fitness.function_3(xis);
                break;
            case "FX4":
                ft = fitness.function_4(xis);
                break;
            case "FX5":
                ft = fitness.function_5(xis);
                break;
            case "FX6":
                ft = fitness.function_6(xis);
                break;
            case "FX7":
                ft = fitness.function_7(xis);
                break;
            case "FX8":
                ft = fitness.function_8(xis);
                break;
            case "FX9":
                ft = fitness.function_9(xis);
                break;
            case "FX10":
                ft = fitness.function_10(xis);
                break;
            case "FX11":
                ft = fitness.function_11(xis);
                break;
            case "FX12":
                ft = fitness.function_12(xis);
                break;
            case "FX13":
                ft = fitness.function_13(xis);
                break;
            case "FX14":
                ft = fitness.function_14(xis);
                break;
            case "FX15":
                ft = fitness.function_15(xis);
                break;
            case "FX16":
                ft = fitness.function_16(xis);
                break;
            case "FX17":
                ft = fitness.function_17(xis);
                break;
            case "FX18":
                ft = fitness.function_18(xis);
                break;
            case "FX19":
                ft = fitness.function_19(xis);
                break;
        }
        return ft;

    }



}
