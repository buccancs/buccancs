package com.shimmerresearch.driverUtilities;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import com.shimmerresearch.driver.calibration.UtilCalibration;

public class MatrixMultiplicationSpeed {

    public static void main(String[] args) {
        double[][] a = {{1, 2, 3}};
        double[][] b = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        long nanotime = System.nanoTime() / 1000000;
        double[][] c = UtilCalibration.matrixMultiplication(a, b);
        long nanotime2 = System.nanoTime() / 1000000;
        System.out.println("Shimmer Library:" + (nanotime2 - nanotime) + "ms");
        System.out.println(c[0][0] + " " + c[0][1] + " " + c[0][2]);

        RealMatrix n = new Array2DRowRealMatrix(a);
        RealMatrix m = new Array2DRowRealMatrix(b);
        long nanotime3 = System.nanoTime() / 1000000;
        RealMatrix o = n.multiply(m);
        long nanotime4 = System.nanoTime() / 1000000;
        System.out.println("Apache Commons:" + (nanotime4 - nanotime3) + "ms");
        System.out.println(o.getData()[0][0] + " " + o.getData()[0][1] + " " + o.getData()[0][2]);
		
				
		

		
    }

}
