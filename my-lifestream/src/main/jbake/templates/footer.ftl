<#macro javascripts>
    <script src="<#nested>js/jquery-1.9.1.min.js"></script>
    <script src="<#nested>js/bootstrap.min.js"></script>
    <script src="<#nested>js/run_prettify.js"></script>
</#macro>

		</div>
		<div id="push"></div>
    </div>

    <div id="footer">
      <div class="container">
        <p class="muted credit">&copy; 2013 | Mixed with <a href="http://twitter.github.com/bootstrap/">Bootstrap v2.3.1</a> | Baked with <a href="http://jbake.org">JBake ${version}</a></p>
      </div>
    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <#if (content.depth)??>
    	<@javascripts>${content.depth}</@javascripts>
    <#elseif (path)??>
    	<@javascripts>${path}</@javascripts>
	<#else>
    	<@javascripts></@javascripts>
    </#if>

  </body>
</html>