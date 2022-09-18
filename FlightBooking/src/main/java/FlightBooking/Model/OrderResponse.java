package FlightBooking.Model;

public class OrderResponse {

	private String applicationFee;
	private String razorpayOrderId;
	private String secretKey;

	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResponse(String applicationFee, String razorpayOrderId, String secretKey) {
		super();
		this.applicationFee = applicationFee;
		this.razorpayOrderId = razorpayOrderId;
		this.secretKey = secretKey;
	}

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
