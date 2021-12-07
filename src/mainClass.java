
import java.io.IOException;
import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) throws IOException, InterruptedException 
	{

		Scanner input = new Scanner(System.in);
		unSecureHttpRequest unSafeObj = new unSecureHttpRequest();

		System.out.println("Please provide your height and weight.");
		System.out.print("Height(cm):");
		String height = input.nextLine();
		System.out.print("Weight(kg):");
		String weight = input.nextLine();

		// Send HttpRequest Method - Get
		unSafeObj.request_Method_Get();
		// Send HttpRequest Method - Post
		unSafeObj.request_Method_Post(height, weight);

	}

}
