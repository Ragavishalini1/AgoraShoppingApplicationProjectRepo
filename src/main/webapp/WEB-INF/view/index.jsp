<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
    <div>
        <div>
            <h1>Agora Application</h1>
            <h2>Hello ${message}</h2>
            
            <p><b>ProductList:</b></p>
            
            <ol>
            	<c:forEach var="product" items="${productList}">
		
					<li><a href="hello/${product.getProductId()}">${product.getProductName()}</a></li>
			
				</c:forEach>
            
            </ol>
             
        </div>
    </div>
</body>
</html>