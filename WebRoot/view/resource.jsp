<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<!-- 公共资源CSS,JS  -->
<!--Css -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/base.css">
<!-- ** Javascript ** -->
<script type="text/javascript" src="${ctx}/js/commons/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/commons/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/commons/package.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/commons/base.js?v=11"></script>
<script type="text/javascript" src="${ctx}/js/commons/YDataGrid.js"></script>