const path = require("path");
const CleanWebpackPlugin = require("clean-webpack-plugin");

module.exports = {
    mode: 'development',

    // 의존성의 시작점
    entry: {
        index: "./src/js/index.js", /* 상대경로 */
        event: "./src/js/event.js"
    },
    // 번들링 된 결과물이 위치하는 곳
    output: {
        filename: "[name].js",
        path: path.resolve(__dirname, './dist') /* 현재 프로젝트 디렉토리에서 ../dist에 위치 */
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/, //node_modules폴더는 패키지 폴더니까 제외처리
                use: ["babel-loader"]    //es6 -> es5
            },
            {
                test: /\.css$/, //웹팩은 모듈을 다루기 때문에 css파일도 .js로 바꿔준다.
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    /*
    plugins: [
        new CleanWebpackPlugin({})  웹팩 실행시 기존에 있던 번들 파일들을 지워줌 인자에 해당 파일 명을 넣어줘야함
    ]*/
};