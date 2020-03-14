<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
    <div>
        <div>
            <h1>Agora Application</h1>
            <h2>${message}</h2>
            
            <p><b>ProductPrice:</b></p>${productPrice}
            <p><b>ProductQuantity:</b></p>${productQuantity}
             
        </div>
    </div>
</body>
</html>