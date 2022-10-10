package FlightBooking.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String userId;
 
    private String razorpayPaymentId;
 
    private String razorpayOrderId;
 
    private String razorpaySignature;

	public Order(String userId, String razorpayPaymentId, String razorpayOrderId, String razorpaySignature) {
		super();
		this.userId = userId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpaySignature = razorpaySignature;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public String setUserId(String userId) {
		return this.userId = userId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public String setRazorpayOrderId(String razorpayOrderId) {
	return	this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpaySignature() {
		return razorpaySignature;
	}

	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}
}
