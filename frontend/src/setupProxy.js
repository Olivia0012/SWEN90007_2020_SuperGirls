const { createProxyMiddleware } = require('http-proxy-middleware');
const proxy = require('http-proxy-middleware');
module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:8080/SWEN90007_2020_SuperGirls',//'https://online-exam-app-supergirls.herokuapp.com/',
      changeOrigin: true,
      pathRewrite: {
        '^/api': ''
      }
    })
  );
};