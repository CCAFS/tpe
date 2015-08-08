<%@ include file="/common/taglibs.jsp"%>
<div id="posts">
	<h1 id="tag-title"></h1>
	<s:iterator value="posts">
		<div class="post">
			<h1>
				<a href="<s:property value='url' />"><s:property value="title" /></a>
			</h1>
			<p>
				<s:property value="title" />
			</p>
		</div>
	</s:iterator>
</div>
