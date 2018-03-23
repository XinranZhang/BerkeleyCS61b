public class NBody {

	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		in.readInt();
		in.readDouble();
		Planet planet1 = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		Planet planet2 = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		Planet planet3 = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		Planet planet4 = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		Planet planet5 = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());	
		
		Planet[] planets = new Planet[] {planet1, planet2, planet3, planet4, planet5};
		return planets;
	}

	public static void main(String[] args) {
		/*Collecting all needed input**/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		readRadius(filename);
		readPlanets(filename);

		/* Drawing the background **/
		// Set the scale to match the radius of the universe
		StdDraw.setScale(-readRadius(filename), readRadius(filename)) ;

		// Draw the image on the background
		StdDraw.picture(0, 0, "starfield.jpg");

		/* Draw one planet - done in class Planet **/

		/* Draw all of the planets **/
		readPlanets(filename)[0].draw();
		readPlanets(filename)[1].draw();
		readPlanets(filename)[2].draw();
		readPlanets(filename)[3].draw();
		readPlanets(filename)[4].draw();

		/* Animation **/
		// create a time variable and set it to 0. set up a loop to loop until this time var is T
		double time = 0;
		Planet[] planets = readPlanets(filename);

		while (time < T ) {
			//Create an xForces array and yForces array
			double[] xForces = new double[5];
			double[] yForces = new double[5];

			//Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays respectively
			xForces[0] = planets[0].calcNetForceExertedByX(planets);
			xForces[1] = planets[1].calcNetForceExertedByX(planets);
			xForces[2] = planets[2].calcNetForceExertedByX(planets);
			xForces[3] = planets[3].calcNetForceExertedByX(planets);
			xForces[4] = planets[4].calcNetForceExertedByX(planets);

			yForces[0] = planets[0].calcNetForceExertedByY(planets);
			yForces[1] = planets[1].calcNetForceExertedByY(planets);
			yForces[2] = planets[2].calcNetForceExertedByY(planets);
			yForces[3] = planets[3].calcNetForceExertedByY(planets);
			yForces[4] = planets[4].calcNetForceExertedByY(planets);

			//Call update on each of the planets
			planets[0].update(dt, xForces[0], yForces[0]);
			planets[1].update(dt, xForces[1], yForces[1]);
			planets[2].update(dt, xForces[2], yForces[2]);
			planets[3].update(dt, xForces[3], yForces[3]);
			planets[4].update(dt, xForces[4], yForces[4]);

			//Draw the background image.
			StdDraw.picture(0, 0, "starfield.jpg");

			//Draw all of the planets
			planets[0].draw();
			planets[1].draw();
			planets[2].draw();
			planets[3].draw();
			planets[4].draw();

			//Pause the animation for 10 milliseconds
			StdDraw.show(10);
		
			//Increase your time variable by dt
			time = time + dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", readRadius(filename));
		for (int i = 0; i < planets.length; i++) {
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}		
	}	
}
