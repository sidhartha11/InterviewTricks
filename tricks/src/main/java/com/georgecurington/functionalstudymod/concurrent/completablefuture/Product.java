/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.completablefuture;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 12, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Product {
private final int productId;
public Product(int productId){
	this.productId = productId;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Product [productId=");
	builder.append(productId);
	builder.append("]");
	return builder.toString();
}
public String getName() {
	// TODO Auto-generated method stub
	return "dummy product name";
}
}