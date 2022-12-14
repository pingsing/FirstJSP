<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="mall/header.jsp" %>
<div class="clear"></div>
<div id="main_img">
  <img src="images/main_img.jpg">
</div>
<div class="clear"></div>
<div id="front">
	<h2>New item</h2>
	<div id="bestProduct">
	  <c:forEach items="${ newProductlist }" var="productVO">
	    <div id="item">
		  <a href="NonageServlet?command=product_detail&pseq=${ productVO.pseq }">
		  	<img src="product_images/${ productVO.image }" />
		    <h3>${ productVO.name }</h3>
			<p>${ productVO.price2 }</p>
		  </a>
	    </div>
	  </c:forEach>
	</div>
	<div class="clear"></div>
	
	<h2>Best Item</h2>
	<div id="bestProduct">
	<c:forEach items="${ newProductlist }" var="productVO">
	    <div id="item">
		  <a href="NonageServlet?command=product_detail&pseq=${ productVO.pseq }"><img src="product_images/${ productVO.image }" />
		    <h3>${ productVO.name }</h3>
			<p>${ productVO.price2 }</p>
		  </a>
	    </div>
	  </c:forEach>
	</div>
	<div class="clear"></div>
</div>
<%@ include file="mall/footer.jsp" %>