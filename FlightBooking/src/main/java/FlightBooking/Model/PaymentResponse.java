package FlightBooking.Model;

public class PaymentResponse {

	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;

	public PaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentResponse(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
		super();
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpaySignature = razorpaySignature;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getRazorpaySignature() {
		return razorpaySignature;
	}

	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}

}
