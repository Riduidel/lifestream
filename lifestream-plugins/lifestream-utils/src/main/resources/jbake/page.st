type=post
status=published
title="<input.title>"
tags=<input.tags; separator=",">
date=<writeDate>
depth=<depth>
uri=<uri>
style=<input.style>
<if(input.additionalHeaders)>
<input.additionalHeaders.keys:{k | $k$=$amap.(k)$\n}>
<endif>
<if(input.source)>
source=<input.source>
<endif>
~~~~~~
<input.text>