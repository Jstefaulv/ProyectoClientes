<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CL"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card-header">
                    <h4>Listado de Clientes</h4>
                </div>
                
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Cliente</th>
                            <th>Saldo</th>
                            <th></th>
                        </tr>
                    </thead>
                    
                    <c:forEach var="cliente" items="${clientes}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${cliente.nombre} ${cliente.apellido}</td>
                            <td>
                                <fmt:formatNumber value="${cliente.saldo}" type="currency"/>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/Controlador?accion=editar&idCliente=${cliente.idCliente}"
                                   class="btn btn-secondary">
                                    Editar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                </table>
            </div>
        </div>
        
    </div>
</section>

<jsp:include page="/WEB-INF/cliente/agregarCliente.jsp"/>