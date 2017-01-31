/**
 * Created by hach on 2017-01-30.
 */
import http from 'http';
import fs from 'fs';

const server = http.createServer((request, response) => {
	console.log(new Date(), '1. Server req/res');

	// html 파일을 읽어 응답합니다.
	fs.readFile('./src/index.html', (error, data) => {
		response.writeHead(200, {
			'Content-Type' : 'text/html'
		});
		response.end(data);
	});
});

server.on('connection', (code) => {
	console.log('2. Connection on ');
});

server.on('request', (code) => {
	console.log('3. Request on ');
});

server.listen(8888, () => {
	console.log('-- Server is starting.............................');
});