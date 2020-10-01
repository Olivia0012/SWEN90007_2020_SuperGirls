var express = require('express');
var proxy = require('http-proxy-middleware');
var path = require('path');
const { createProxyMiddleware } = require('http-proxy-middleware');
var serveStatic = require('serve-static');
app = express();
app.use(serveStatic(__dirname));
app.use(
	'/api',
	createProxyMiddleware({
		target: 'https://online-exam-app-supergirls.herokuapp.com/',
		changeOrigin: true,
		pathRewrite: {
			'^/api': ''
		}
	})
);

app.get('/oea', function (req, res) { // this one works
//	return res.send('pong');
	res.sendFile(path.join(__dirname, '/build'));
   });

var port = process.env.PORT || 3000;
app.listen(port);
console.log('server started ' + port);
