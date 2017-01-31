import express from 'express';
import bodyParse from 'body-parser';
import api from './routes';
import webpackDevMiddleware  from 'webpack-dev-middleware';
import webpackHotMiddleware  from 'webpack-hot-middleware';
import webpack from 'webpack';
import config from '../../config/webpack.config.dev';

const app = express();
let port = 8000;
let devPort = 3000;

if(process.env.NODE_ENV == 'development') {
	console.log('Server is running on development mode');

	let compiler = webpack(config);
	// app.use(webpackDevMiddleware(compiler, { noInfo: true, publicPath: config.output.publicPath }))
	// app.use(webpackHotMiddleware(compiler))
	// // devServer.listen(devPort, () => {
	// // 	console.log('webpack-dev-server is listening on port', devPort);
	// // });
	app.use(webpackDevMiddleware(compiler, {
		publicPath: config.output.publicPath,
		stats: {colors: true}
	}));

	app.use(webpackHotMiddleware(compiler, {
		log: console.log
	}));
}

// SETUP MIDDLEWARE
app.use(bodyParse.json());

// SETUP STATIC CONTENTS - REACT PROJECT
app.use('/', express.static(__dirname + '/../../public'));

// LOAD API FROM ROUTES
// TO BE IMPLEMENTED

app.use('/api', api);

app.listen(port, () => {
	console.log('Express is listening on port', port);
});



