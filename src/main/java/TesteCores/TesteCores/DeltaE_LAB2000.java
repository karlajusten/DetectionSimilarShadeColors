package TesteCores.TesteCores;

/*
 * Totally based on: https://github.com/zschuessler/DeltaE/blob/master/src/dE00.js
 */
public class DeltaE_LAB2000 {
	
	double deltaLPrime, LBar, C1, C2, CBar, aPrime1, aPrime2, CPrime1, CPrime2,
	CBarPrime, deltaCPrime, SsubL, SsubC;
	double hPrime1, hPrime2, deltahPrime, deltaHPrime, HBarPrime, T, SsubH, RsubT;
	double ksubL, ksubC, ksubH;
	LAB x1, x2;
	
	DeltaE_LAB2000(LAB x1, LAB x2){
	    
		this.x1 = x1;
		this.x2 = x2;
		
		//this.weights = weights || {};
	    this.ksubL = 1; // this.weights.lightness || 1;
	    this.ksubC = 1; // this.weights.chroma || 1;
	    this.ksubH = 1; // this.weights.hue || 1;
	    
	    // Delta L Prime
		this.deltaLPrime = x2.getL() - x1.getL();
	    
	    // L Bar
	    this.LBar = (x1.getL() + x2.getL()) / 2;
	    
	    // C1 & C2
	    this.C1 = Math.sqrt(Math.pow(x1.getA(), 2) + Math.pow(x1.getB(), 2));
	    this.C2 = Math.sqrt(Math.pow(x2.getA(), 2) + Math.pow(x2.getB(), 2));
	    
	    // C Bar
	    this.CBar = (C1 + C2) / 2;
	    
	    // A Prime 1
	    this.aPrime1 = x1.getA() +
	        (x1.getA() / 2) *
	        (1 - Math.sqrt(
	        		 Math.pow(this.CBar, 7) /
	            ( Math.pow(this.CBar, 7) +  Math.pow(25, 7))
	        ));

	    // A Prime 2
	    this.aPrime2 = x2.getA() +
	        (x2.getA() / 2) *
	        (1 - Math.sqrt(
	        		Math.pow(this.CBar, 7) /
	            (Math.pow(this.CBar, 7) + Math.pow(25, 7))
	        ));

	    // C Prime 1
	    this.CPrime1 = Math.sqrt(
	    	Math.pow(this.aPrime1, 2) +
	    	Math.pow(x1.getB(), 2)
	    );
	    
	    // C Prime 2
	    this.CPrime2 = Math.sqrt(
	    	Math.pow(this.aPrime2, 2) +
	    	Math.pow(x2.getB(), 2)
	    );
	    
	    // C Bar Prime
	    this.CBarPrime = (this.CPrime1 + this.CPrime2) / 2;
	    
	    // Delta C Prime
	    this.deltaCPrime = this.CPrime2 - this.CPrime1; 
	    
	    // S sub L
	    this.SsubL = 1 + (
	        (0.015 * Math.pow(this.LBar - 50, 2)) /
	        Math.sqrt(20 + Math.pow(this.LBar - 50, 2))
	    );
	    
	    // S sub C
	    this.SsubC = 1 + 0.045 * this.CBarPrime;
	    
	    
	}

	/**
	 * Returns the deltaE value.
	 * @method
	 * @returns {number}
	 */
	public double getDeltaE() {
	    
	    // h Prime 1
	    this.hPrime1 = this.gethPrime1();
	    
	    // h Prime 2
	    this.hPrime2 = this.gethPrime2();
	    
	    // Delta h Prime
	    this.deltahPrime = this.getDeltahPrime(); 
	    
	    // Delta H Prime
	    this.deltaHPrime = 2 * Math.sqrt(this.CPrime1 * this.CPrime2) * Math.sin(this.degreesToRadians(this.deltahPrime) / 2);
	    
	    // H Bar Prime
	    this.HBarPrime = this.getHBarPrime();
	    
	    // T
	    this.T = this.getT();
	    
	    // S sub H
	    this.SsubH = 1 + 0.015 * this.CBarPrime * this.T;
	    
	    // R sub T
	    this.RsubT = this.getRsubT(); 
	    
	    // Put it all together!
	    double lightness = this.deltaLPrime / (this.ksubL * this.SsubL);
	    double chroma = this.deltaCPrime / (this.ksubC * this.SsubC);
	    double hue = this.deltaHPrime / (this.ksubH * this.SsubH);
	   
	    return Math.sqrt(
	    		Math.pow(lightness, 2) +
	    		Math.pow(chroma, 2) +
	    		Math.pow(hue, 2) +
	        this.RsubT * chroma * hue
	    );
	};

	/**
	 * Returns the RT variable calculation.
	 * @method
	 * @returns {number}
	 */
	double getRsubT() {
	    
	    return -2 *
	    		Math.sqrt(
	        		Math.pow(this.CBarPrime, 7) /
	            (Math.pow(this.CBarPrime, 7) + Math.pow(25, 7))
	        ) *
	    		Math.sin(this.degreesToRadians(
	            60 *
	            Math.exp(
	                -(
	                		Math.pow(
	                        (this.HBarPrime - 275) / 25, 2
	                    )
	                )
	            )
	        ));
	};

	/**
	 * Returns the T variable calculation.
	 * @method
	 * @returns {number}
	 */
	public double getT() {
	    
	    return 1 -
	        0.17 * Math.cos(this.degreesToRadians(this.HBarPrime - 30)) +
	        0.24 * Math.cos(this.degreesToRadians(2 * this.HBarPrime)) +
	        0.32 * Math.cos(this.degreesToRadians(3 * this.HBarPrime + 6)) -
	        0.20 * Math.cos(this.degreesToRadians(4 * this.HBarPrime - 63));
	};

	/**
	 * Returns the H Bar Prime variable calculation.
	 * @method
	 * @returns {number}
	 */
	public double getHBarPrime() {
	    
	    if (Math.abs(this.hPrime1 - this.hPrime2) > 180) {
	        return (this.hPrime1 + this.hPrime2 + 360) / 2;
	    }
	    
	    return (this.hPrime1 + this.hPrime2) / 2;
	};

	/**
	 * Returns the Delta h Prime variable calculation.
	 * @method
	 * @returns {number}
	 */
	public double getDeltahPrime() {
	    
	    // When either C′1 or C′2 is zero, then Δh′ is irrelevant and may be set to
	    // zero.
	    if (0 == this.C1 || 0 == this.C2) {
	        return 0;
	    }
	    
	    if (Math.abs(this.hPrime1 - this.hPrime2) <= 180) {
	        return this.hPrime2 - this.hPrime1;
	    }
	    
	    if (this.hPrime2 <= this.hPrime1) {
	        return this.hPrime2 - this.hPrime1 + 360;
	    } else {
	        return this.hPrime2 - this.hPrime1 - 360;
	    }
	};

	/**
	 * Returns the h Prime 1 variable calculation.
	 * @method
	 * @returns {number}
	 */
	public double gethPrime1() {
	    return this._gethPrimeFn(this.x1.getB(), this.aPrime1);
	};

	/**
	 * Returns the h Prime 2 variable calculation.
	 * @method
	 * @returns {number}
	 */
	public double gethPrime2() {
	    return this._gethPrimeFn(this.x2.getB(), this.aPrime2);
	};

	/**
	 * A helper function to calculate the h Prime 1 and h Prime 2 values.
	 * @method
	 * @private
	 * @returns {number}
	 */
	public double _gethPrimeFn(double x, double y) {
	    double hueAngle;
	    
	    if (x == 0 && y == 0) {
	        return 0;
	    }
	    
	    hueAngle = this.radiansToDegrees(Math.atan2(x, y));
	    
	    if (hueAngle >= 0) {
	        return hueAngle;
	    } else {
	        return hueAngle + 360;
	    }
	}

	/**
	 * Gives the radian equivalent of a specified degree angle.
	 * @method
	 * @returns {number}
	 */
	public double radiansToDegrees(double radians) {
	    return radians * (180 / Math.PI);
	};

	/**
	 * Gives the degree equivalent of a specified radian.
	 * @method
	 * @returns {number}
	 */
	public double degreesToRadians( double degrees) {
	    return degrees * (Math.PI / 180);
	}
}
