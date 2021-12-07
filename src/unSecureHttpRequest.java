import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class unSecureHttpRequest 
{
	// Create one instance and can reuse it.
	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public void request_Method_Get() throws IOException, InterruptedException 
	{
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create("http://127.0.0.1:5100/"))
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// Show the status code, or comment it.
		System.out.println("Response status code:" + response.statusCode());
		// Show the response body.
		System.out.println(response.body());
	}

	public void request_Method_Post(String height, String weight) throws IOException, InterruptedException 
	{
		Map<Object, Object> data = new HashMap<>();
		data.put("height", height);
		data.put("weight", weight);

		HttpRequest request = HttpRequest.newBuilder()
				.POST(buildFormData(data))
				.uri(URI.create("http://127.0.0.1:5100/post"))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// Show the status code, or comment it.
		System.out.println("Response status code:" + response.statusCode());
		// Show the response body.
		System.out.println(response.body());
	}

	// Build form data from Map
	private static HttpRequest.BodyPublisher buildFormData(Map<Object, Object> data) 
	{
		var builder = new StringBuilder();
		for (Map.Entry<Object, Object> entry : data.entrySet()) 
		{
			if (builder.length() > 0) 
			{
				builder.append("&");
			}
			builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
			builder.append("=");
			builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
		}
		// Check the Request String.
		// System.out.println(builder.toString());
		return HttpRequest.BodyPublishers.ofString(builder.toString());
	}

}
