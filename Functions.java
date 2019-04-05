/*

 Main Paper 
 J. M. Abdullah and T. A. Rashid, "Fitness Dependent Optimizer: Inspired by the Bee Swarming Reproductive Process," in IEEE Access.
doi: 10.1109/ACCESS.2019.2907012
keywords: {Optimization;Heuristic algorithms;Classification algorithms;Genetic algorithms;Artificial bee colony algorithm;Particle swarm optimization;Optimization;Swarm Intelligence;Evolutionary Computation;Metaheuristic Algorithms;Fitness Dependent Optimizer;FDO},
URL: http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=8672851&isnumber=6514899
 
 */


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class Functions {
    String functionName;

    public Functions(String functionName) {
        this.functionName = functionName;
    }

    public Double function_FM(ArrayList<Double> xs) {
        Double fitness = 0.0;
        Double y = 0.0;
        Double yn = 0.0;
        final double PI = Math.PI;
        double theta = (2*PI)/100;
        double a1 = xs.get(0);
        double w1 = xs.get(1);
        double a2 = xs.get(2);
        double w2 = xs.get(3);
        double a3 = xs.get(4);
        double w3 = xs.get(5);

        for(int i=0; i <xs.size(); i++){
            double x = xs.get(i).doubleValue();
            if(x > 6.3){
                xs.set(i, 6.3* (new Random().nextDouble()));
            }else if(x < -6.4){
                xs.set(i, -6.4* (new Random().nextDouble()));
            }
        }

        for(int t =0; t <=100; t++){
            y = a1 * Math.sin(w1*t*theta + a2* Math.sin(w2*t*theta + a3 *Math.sin(w3*t*theta)));
            yn = 1.0 * Math.sin(5.0*t*theta + 1.5* Math.sin(4.8*t*theta + 2.0 *Math.sin(4.9*t*theta)));
            fitness += Math.pow(y-yn, 2);
        }
        return fitness;
    }
    public Double function_antenna(ArrayList<Double> xs) {
        Double fitness ;
        Double sum = 0.0;
        final double PI = Math.PI;
        int evalationAngle = 45;
        int beamangle = 90;
        Random r = new Random();
        for (int j = 0; j < 4; j++) {
            double x= xs.get(j).doubleValue();
            if ( x > 2.0) {
                xs.set(j,  2.0*r.nextDouble() );
            }else if(x <= 0.125 ){
                xs.set(j,  0.125+ x* r.nextDouble());
            }
        }
        for (int i = 0; i < 4; i++) {
            double xi= xs.get(i).doubleValue();
            for (int j = i; j < 4; j++) {
                if(i != j) {
                   double xj = xs.get(j).doubleValue();
                    if (xi > xj && xi - xj < 0.26) {
                        xs.set(i, xi+ 0.125);
                        xs.set(j,  xj- 0.125);
                    } else if (xj > xi && xj - xi < 0.26) {
                        xs.set(j,  xj+ 0.125);
                        xs.set(i, xi- 0.125);
                    }
                }
            }
        }

        for (Double x : xs) {
            sum += Math.cos(2 * PI * x * (Math.cos(evalationAngle)- Math.cos(beamangle)))+ Math.cos(2.25* 2 * PI  * (Math.cos(evalationAngle)- Math.cos(beamangle)));
        }
        fitness = 20 * Math.log(Math.abs(sum));
        return fitness;
    }

    public Double function_1(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (Double x : xs) {
            x = x - 30;
            fitness += Math.pow(x, 2);
        }
        return fitness;
    }

    public Double function_2(ArrayList<Double> xs) {
        Double fitness = 0.0;
        Double x_sum = 0.0;
        Double x_multiply = 1.0;
        for (double x : xs) {
            x= x-3;
            x = Math.abs(x);
            x_multiply *= x;
            x_sum += x;
        }

        fitness = x_sum + x_multiply;
        return fitness;
    }

    public Double function_3(ArrayList<Double> xs) {
        Double fitness = 0.0;

        for (int i = 0; i < xs.size(); i++) {
            double sumXs = 0.0;
            for (int j = 0; j < i; j++) {
                sumXs += xs.get(j) - 30;
            }
            fitness += Math.pow(sumXs, 2);
        }

        return fitness;
    }

    public Double function_4(ArrayList<Double> xs) {
        Double fitness = Math.abs(xs.get(0));
        for (int i = 0; i < xs.size(); i++) {
            if ((double) Math.abs(xs.get(i)) > (double) fitness) {
                fitness = Math.abs(xs.get(i) - 30);
            }
        }
        return fitness;
    }

    public Double function_5(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (int i = 0; i < xs.size() - 1; i++) {
            double x = xs.get(i) - 15;
            fitness += (100 * Math.pow(((xs.get(i + 1) - 15) - Math.pow(x, 2)), 2)) + Math.pow((x - 1), 2);
        }
        return fitness;
    }

    public Double function_6(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 750;
            fitness += Math.pow(x + 0.5, 2);
        }
        return fitness;
    }

    public Double function_7(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 0.25;
            fitness += (i+1) * Math.pow(x, 4);
        }
        return fitness + Math.random();
    }

    public Double function_8(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 300;
            fitness += Math.pow(-x, 2) * Math.sin(Math.sqrt(Math.abs(x)));
        }
        return fitness;
    }

    public Double function_9(ArrayList<Double> xs) {
        Double fitness = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 2;
            fitness += Math.pow(x, 2) - (10 * Math.cos(2 * Math.PI * x)) + 10;
        }
        return fitness;
    }

    public Double function_10(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum1 = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            sum1 += Math.pow(xs.get(i), 2);
            sum2 += Math.cos(2 * Math.PI * xs.get(i));
        }
        double n = xs.size();
        sum1 = -0.2 * Math.sqrt(sum1 / n);
        fitness = -20 * Math.exp(sum1) - Math.exp(sum2 / n) + 20 + Math.E;
        return fitness;
    }

    public Double function_11(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum = 0.0;
        double prod = 1;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 400;
            sum += Math.pow(x, 2);
            prod *= Math.cos(x / Math.sqrt(i + 1));
            //    
        }
        //   System.out.print("prod" +prod);
        fitness = (1 / 4000) * sum - prod + 1;
        return fitness;
    }

    public Double function_12(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum = 0.0;
        double u = 0.0;
        double yn = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x = xs.get(i) - 30;
            double y = 1 + ((x + 1) / 4);
            sum += Math.pow((y - 1), 2) * Math.pow((1 + (10 * Math.sin(Math.PI * y + 1))), 2);
            u += Ufunc(x, 10, 100, 4);
        }
        yn = 1 + (((xs.get(xs.size() - 1) - 30) + 1) / 4);
        fitness = (Math.PI / xs.size()) * ((10 * Math.pow(Math.sin(Math.PI * (1 + ((xs.get(0) - 30) + 1) / 4)), 2)) + sum + Math.pow(yn - 1, 2)) + u;
        return fitness;
    }

    public Double function_13(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum = 0.0;
        double sum2 = 0.0;
        double x_1 = 100 - xs.get(0);
        double x_n = 100 - xs.get(xs.size()-1);
        final double PI = Math.PI;
        double u = 0.0;
        for (int i = 0; i < xs.size(); i++) {
            double x =  xs.get(i) - 100;
            sum2 = Math.pow(Math.sin(3 * PI * x + 1), 2);
            sum += Math.pow(x - 1, 2) * (1 + sum2);
            u += Ufunc(x, 5, 100, 4);
        }
        fitness = 0.1 * ((Math.pow(Math.sin(3 * PI * (x_1)), 2)) +
                sum + Math.pow( x_n - 1, 2) *
                (1 + Math.pow(Math.sin(2 * PI * (x_n)), 2))) + u;
        return fitness;
    }

    private double Ufunc(double x, double a, double k, double m) {
        double u = 0.0;
        if (x > a) {
            u = k * Math.pow((x - a), m);
        } else if (x < a && x > -a) {
            u = 0.0;
        } else if (x < -a) {
            u = k * Math.pow((-x - a), m);
        }

        return u;
    }

    public Double function_14(ArrayList<Double> xs) {
        Double fitness = 0.0;

        for (int i = 0; i < xs.size(); i++) {
            double x = (1 + xs.get(i)) * 0.05;
            fitness += Math.pow(x, 2);
        }

        return fitness;
    }

    public Double function_15(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum = 0.0;
        double mul = 1.0;

        for (int i = 0; i < xs.size(); i++) {
            double x = (1 + xs.get(i)) * 0.05;
            sum += Math.pow(x, 2) / 400;
            mul *= Math.cos(x / Math.sqrt(Math.abs(x)));
        }
        fitness = sum - mul + 1;
        return fitness;
    }

    public Double function_16(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double sum = 0.0;
        double mul = 1.0;
////        double x1 = xs.get(0);
////        double x2 = xs.get(1);
////        fitness = 4 * Math.pow(x1, 2) - 2.1 * Math.pow(x1, 4) +(Math.pow(x1, 6) / 3) + x1 * x2 - 4 * Math.pow(x2, 2) + 4 * Math.pow(x2, 4);
//        double sum = 0.0;
//        double bee.multi = 1.0;
//        for (int i = 0; i < xs.size(); i++) {
//            double x = xs.get(i);
//            sum += Math.pow(x, 2) / 4000;
//            bee.multi *= Math.cos(x / Math.sqrt(i + 1));
//        }
//        fitness = sum - bee.multi + 1;
//        return fitness;
//    }
//
//    public Double function_17(ArrayList<Double> xs) {
//        Double fitness = 0.0;
//        double x1 = xs.get(0);
//        double x2 = xs.get(1);
//        fitness = Math.pow((x2 - (Math.pow(x1, 2)) * 5.1 / (4 * Math.pow(Math.PI, 2)) + 5 / Math.PI * x1 - 6), 2) + 10 * (1 - 1 / (8 * Math.PI)) * Math.cos(x1) + 10;
        for (int i = 0; i < xs.size(); i++) {
            double x = (1 + xs.get(i));
            sum += Math.pow(x, 2) / 400;
            mul *= Math.cos(x / Math.sqrt(Math.abs(x)));
        }
        fitness = sum - mul + 1;
        return fitness;
    }

    public Double function_17(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double x1 = ((1 + xs.get(0)) * (5 / 32));
        double x2 = ((1 + xs.get(1)) * (5 / 32));
        double x3 = (1 + xs.get(2));
        double x4 = (1 + xs.get(3));
        double x5 = (1 + xs.get(4) * (5 / 0.5));
        double x6 = (1 + xs.get(5) * (5 / 0.5));
        double x7 = (1 + xs.get(6) * (5 / 100));
        double x8 = (1 + xs.get(7) * (5 / 100));
        double x9 = (1 + xs.get(8) * (5 / 100));
        double x10 = (1 + xs.get(9) * (5 / 100));

        double a = 20;
        double b = 0.2;
        double c = 2 * Math.PI;
        double sum = Math.pow(x1, 2) + Math.pow(x2, 2);

        double sumCos = Math.cos(Math.abs(x1) * c) + Math.cos(Math.abs(x2) * c);
        //  System.out.println("sumCos "+sumCos);
        double ackley = -a * Math.exp(-b * Math.sqrt(Math.abs(1 / 2 * sum))) - Math.exp(1 / 2 * sumCos) + a + Math.exp(1);

        sum = Math.pow(x3 - 10 * Math.cos(2 * Math.PI * x3), 2) + Math.pow(x4 - 10 * Math.cos(2 * Math.PI * x4), 2);
        double rastrigin = 20 + sum;

        double weierstrass = 0.5 * Math.sin(2 * x5) + 0.25 * Math.sin(4 * x6);

        sum = Math.pow(x7, 2) / 400 + Math.pow(x8, 2) / 400;
        double mul = Math.cos(x7 / Math.sqrt(Math.abs(x7))) * Math.cos(x8 / Math.sqrt(Math.abs(x8)));
        double griewank = sum - mul + 1;

        double sphere = Math.pow(x9, 2) + Math.pow(x10, 2);

        fitness = ackley + rastrigin + weierstrass + griewank + sphere;
        return fitness;

    }

    public Double function_18(ArrayList<Double> xs) {
        Double fitness = 0.0;
        double x1 = ((1 + xs.get(0)) * (1 / 5));
        double x2 = ((1 + xs.get(1)) * (1 / 5));
        double x3 = (1 + xs.get(2) * 10);
        double x4 = (1 + xs.get(3) * 10);
        double x5 = (1 + xs.get(4) * (0.05));
        double x6 = (1 + xs.get(5) * (0.05));
        double x7 = (1 + xs.get(6) * (5 / 32));
        double x8 = (1 + xs.get(7) * (5 / 100));
        double x9 = (1 + xs.get(8) * (5 / 100));
        double x10 = (1 + xs.get(9) * (5 / 100));
        double sum = 0.0;


        sum = Math.pow(x1 - 10 * Math.cos(2 * Math.PI * x1), 2) + Math.pow(x2 - 10 * Math.cos(2 * Math.PI * x2), 2);
        double rastrigin = 20 + sum;

        double weierstrass = 0.5 * Math.sin(2 * x3) + 0.25 * Math.sin(4 * x4);

        sum = Math.pow(x5, 2) / 400 + Math.pow(x5, 2) / 400;
        double mul = Math.cos(x5 / Math.sqrt(Math.abs(x5))) * Math.cos(x6 / Math.sqrt(Math.abs(x6)));
        double griewank = sum - mul + 1;

        double a = 20;
        double b = 0.2;
        double c = 2 * Math.PI;
        sum = Math.pow(x7, 2) + Math.pow(x8, 2);
        double sumCos = Math.cos(Math.abs(x7) * c) + Math.cos(Math.abs(x8) * c);
        double ackley = -a * Math.exp(-b * Math.sqrt(Math.abs(1 / 2 * sum))) - Math.exp(1 / 2 * sumCos) + a + Math.exp(1);


        double sphere = Math.pow(x9, 2) + Math.pow(x10, 2);

        fitness = ackley + rastrigin + weierstrass + griewank + sphere;
        return fitness;
    }

    public Double function_19(ArrayList<Double> xs) {
        Double fitness = 0.0;
//        double[][] aH = {
//            {3, 10, 30},
//            {0.1, 10, 35},
//            {3, 10, 30},
//            {0.1, 10, 35}
//        };
//        double[] cH = {1, 1.2, 3, 3.2};
//        double[][] pH = {
//            {0.3689, 0.117, 0.2673},
//            {0.4699, 0.4387, 0.747},
//            {0.1091, 0.8732, 0.5547},
//            {0.03815, 0.5743, 0.8828}
//        };
//
//        for (int i = 0; i < 4; i++) {
//            double sum = 0.0;
//            for (int j = 0; j < 3; j++) {
//                double x = xs.get(j);
//                sum += aH[i][j] * Math.pow((x - pH[i][j]), 2);
//            }
//            fitness = fitness - cH[i] * Math.exp(-sum);
//        }

        double x1 = (0.1 + xs.get(0) * (0.1 * 1 / 5));
        double x2 = (0.2 + xs.get(1) * (0.2 * 1 / 5));
        double x3 = (0.3 + xs.get(2) * 0.3 * 10);
        double x4 = (0.4 + xs.get(3) * 0.4 * 10);
        double x5 = (0.5 + xs.get(4) * (0.5 * 0.05));
        double x6 = (0.6 + xs.get(5) * (0.6 * 0.05));
        double x7 = (0.7 + xs.get(6) * 0.7 * (5 / 32));
        double x8 = (0.8 + xs.get(7) * 0.8 * (5 / 100));
        double x9 = (0.9 + xs.get(8) * 0.9 * (5 / 100));
        double x10 = (1 + xs.get(9) * (5 / 100));
        double sum = 0.0;


        sum = Math.pow(x1 - 10 * Math.cos(2 * Math.PI * x1), 2) + Math.pow(x2 - 10 * Math.cos(2 * Math.PI * x2), 2);
        double rastrigin = 20 + sum;

        double weierstrass = 0.5 * Math.sin(2 * x3) + 0.25 * Math.sin(4 * x4);

        sum = Math.pow(x5, 2) / 400 + Math.pow(x5, 2) / 400;
        double mul = Math.cos(x5 / Math.sqrt(Math.abs(x5))) * Math.cos(x6 / Math.sqrt(Math.abs(x6)));
        double griewank = sum - mul + 1;

        double a = 20;
        double b = 0.2;
        double c = 2 * Math.PI;
        sum = Math.pow(x7, 2) + Math.pow(x8, 2);
        double sumCos = Math.cos(Math.abs(x7) * c) + Math.cos(Math.abs(x8) * c);
        double ackley = -a * Math.exp(-b * Math.sqrt(Math.abs(1 / 2 * sum))) - Math.exp(1 / 2 * sumCos) + a + Math.exp(1);


        double sphere = Math.pow(x9, 2) + Math.pow(x10, 2);

        fitness = ackley + rastrigin + weierstrass + griewank + sphere;

        return fitness;
    }

    public double[] function_20(ArrayList<Double> xs) {
        double[] fx = {0.0, 0.0};
        int n = xs.size();
        fx[0] = xs.get(0);
        double sum = 0.0;
        for (int i = 1; i < n; i++) {
            sum += xs.get(i);
        }
        double gx = 1 + 9 / (n - 1) * sum;
        double h = 1 - Math.sqrt(Math.abs(fx[0] / gx));
        fx[1] = gx * h;
        return fx;
    }

    public double[] function_21(ArrayList<Double> xs) {
        double[] fx = {0.0, 0.0};
        int n = xs.size();
        fx[0] = xs.get(0);
        double sum = 0.0;
        for (int i = 1; i < n; i++) {
            sum += xs.get(i);
        }
        double gx = 1 + 9 / (n - 1) * sum;
        double h = 1 - Math.pow(fx[0] / gx, 2);
        fx[1] = gx * h;
        return fx;
    }

    public double[] function_22(ArrayList<Double> xs) {
        double[] fx = {0.0, 0.0};
        int n = xs.size();
        fx[0] = xs.get(0);
        double sum = 0.0;
        for (int i = 1; i < n; i++) {
            sum += xs.get(i);
        }
        double gx = 1 + (9 / 29) * sum;
        double h = 1 - Math.sqrt(Math.abs(fx[0] / gx)) - (fx[0] / gx) * Math.sin(10 * Math.PI * fx[0]);
        fx[1] = gx * h;
        return fx;
    }

    public double[] function_23(ArrayList<Double> xs) {
        double[] fx = {0.0, 0.0};
        int n = xs.size();
        fx[0] = xs.get(0);
        double sum = 0.0;
        for (int i = 1; i < n; i++) {
            sum += xs.get(i);
        }
        double gx = 1 + (9 / (n - 1)) * sum;
        double h = 1 - fx[0] / gx;
        fx[1] = gx * h;
        return fx;
    }

    public double[] function_24(ArrayList<Double> xs) {
        double[] fx = {0.0, 0.0, 0.0};
        int n = xs.size();
        fx[0] = xs.get(0);
        fx[1] = xs.get(1);
        double sum = 0.0;
        for (int i = 1; i < n; i++) {
            sum += xs.get(i);
        }
        double gx = 1 + (9 / (n - 1)) * sum;
        double h1 = 1 - Math.pow(fx[0] / gx, 2);
        double h2 = 1 - Math.pow(fx[1] / gx, 2);
        fx[2] = gx * h1 * h2;
        return fx;
    }

    int getDimensions() {
        int dimensions = 2;
        switch (functionName) {

            case "function_FM":
                dimensions = 6;
                break;
            case "function_antenna":
                dimensions = 4;
                break;
            case "FX1":
                dimensions = 10;
                break;
            case "FX2":
                dimensions = 10;
                break;
            case "FX3":
                dimensions = 10;
                break;
            case "FX4":
                dimensions = 10;
                break;
            case "FX5":
                dimensions = 10;
                break;
            case "FX6":
                dimensions = 10;
                break;
            case "FX7":
                dimensions = 10;
                break;
            case "FX8":
                dimensions = 10;
                break;
            case "FX9":
                dimensions = 10;
                break;
            case "FX10":
                dimensions = 10;
                break;
            case "FX11":
                dimensions = 10;
                break;
            case "FX12":
                dimensions = 10;
                break;
            case "FX13":
                dimensions = 10;
                break;
            case "FX14":
                dimensions = 2;
                break;
            case "FX15":
                dimensions = 4;
                break;
            case "FX16":
                dimensions = 10;
                break;
            case "FX17":
                dimensions = 10;
                break;
            case "FX18":
                dimensions = 10;
                break;
            case "FX19":
                dimensions = 10;
                break;
            case "FX20":
                dimensions = 5;
                break;
            case "FX21":
                dimensions = 2;
                break;
            case "FX22":
                dimensions = 5;
                break;
            case "FX23":
                dimensions = 5;
                break;
            case "FX24":
                dimensions = 5;
                break;
        }
        return dimensions;
    }

    double[] getUpperBound() {
        double[] upperBound = {0, 0};
        switch (functionName) {
            case "function_FM":
                upperBound[0] = 6.35;
                break;
            case "function_antenna":
                upperBound[0] = 2.0;
                break;
            case "FX1":
                upperBound[0] = 100;
                break;
            case "FX2":
                upperBound[0] = 10;
                break;
            case "FX3":
                upperBound[0] = 100;
                break;
            case "FX4":
                upperBound[0] = 100;
                break;
            case "FX5":
                upperBound[0] = 30;
                break;
            case "FX6":
                upperBound[0] = 100;
                break;
            case "FX7":
                upperBound[0] = 1.28;
                break;
            case "FX8":
                upperBound[0] = 500;
                break;
            case "FX9":
                upperBound[0] = 5.12;
                break;
            case "FX10":
                upperBound[0] = 32;
                break;
            case "FX11":
                upperBound[0] = 600;
                break;
            case "FX12":
                upperBound[0] = 50;
                break;
            case "FX13":
                upperBound[0] = 50;
                break;
            case "FX14":
                upperBound[0] = 65.536;
                break;
            case "FX15":
                upperBound[0] = 5;
                break;
            case "FX16":
                upperBound[0] = 5;
                break;
            case "FX17":
                upperBound[0] = 5;

                break;
            case "FX18":
                upperBound[0] = 5;
                break;
            case "FX19":
                upperBound[0] = 5;
                break;
            case "FX20":
                upperBound[0] = 1;
                break;
            case "FX21":
                upperBound[0] = 1;
                break;
            case "FX22":
                upperBound[0] = 1;
                break;
            case "FX23":
                upperBound[0] = 1;
                break;
            case "FX24":
                upperBound[0] = 1;
                break;
        }
        return upperBound;
    }

    double[] getLowerBound() {
        double[] Lower = {0, 0};
        switch (functionName) {

            case "function_FM":
                Lower[0] = -6.4;
                break;
            case "function_antenna":
                Lower[0] = 0;
                break;
            case "FX1":
                Lower[0] = -100;
                break;
            case "FX2":
                Lower[0] = -10;
                break;
            case "FX3":
                Lower[0] = -100;
                break;
            case "FX4":
                Lower[0] = -100;
                break;
            case "FX5":
                Lower[0] = -30;
                break;
            case "FX6":
                Lower[0] = -100;
                break;
            case "FX7":
                Lower[0] = -1.28;
                break;
            case "FX8":
                Lower[0] = -500;
                break;
            case "FX9":
                Lower[0] = -5.12;
                break;
            case "FX10":
                Lower[0] = -32;
                break;
            case "FX11":
                Lower[0] = -600;
                break;
            case "FX12":
                Lower[0] = -50;
                break;
            case "FX13":
                Lower[0] = -50;
                break;
            case "FX14":
                Lower[0] = -65.536;
                break;
            case "FX15":
                Lower[0] = -5;
                break;
            case "FX16":
                Lower[0] = -5;
                break;
            case "FX17":
                Lower[0] = -5;
                break;
            case "FX18":
                Lower[0] = -5;
                break;
            case "FX19":
                Lower[0] = -5;
                break;
            case "FX20":
                Lower[0] = 0;
                break;
            case "FX21":
                Lower[0] = 0;
                break;
            case "FX22":
                Lower[0] = 0;
                break;
            case "FX23":
                Lower[0] = 0;
                break;
            case "FX24":
                Lower[0] = 0;
                break;
        }
        return Lower;
    }
}
