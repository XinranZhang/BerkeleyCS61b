public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance (Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double distance = Math.sqrt(dx * dx +dy * dy);
		return distance;
	}

	public double calcForceExertedBy (Planet p){
		double G = 6.67 / Math.pow(10, 11);
		double m1 = this.mass;
		double m2 = p.mass;
		double distance = this.calcDistance(p);
		double f = G * m1 * m2 / (distance * distance);
		return f;
	}

	public double calcForceExertedByX (Planet p) {
		double F = this.calcForceExertedBy (p);
		double dx = p.xxPos - this.xxPos;
		double distance = this.calcDistance(p);
		double FX = F * dx / distance;
		return FX;
	}

	public double calcForceExertedByY (Planet p) {
		double F = this.calcForceExertedBy (p);
		double dy = p.yyPos - this.yyPos;
		double distance = this.calcDistance(p);
		double FY = F * dy / distance;
		return FY;
	}

	public double calcNetForceExertedByX (Planet[] Planets) {
		int i = 0;
		double NetForceX = 0; 
		for (i = 0; i < Planets.length; i++) {
			if (!Planets[i].equals(this)) {
				NetForceX = NetForceX + calcForceExertedByX(Planets[i]);
			} 
		}
		return NetForceX;
	}

	public double calcNetForceExertedByY (Planet[] Planets) {
		int i = 0;
		double NetForceY = 0; 
		for (i = 0; i < Planets.length; i++) {
			if (!Planets[i].equals(this)){
				NetForceY = NetForceY + calcForceExertedByY(Planets[i]);
			} 
		}
		return NetForceY;
	}

	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY/ this.mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}


}