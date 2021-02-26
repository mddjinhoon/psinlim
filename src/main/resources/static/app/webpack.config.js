const path = require("path");
const CleanWebpackPlugin = require("clean-webpack-plugin");

module.exports = {
    entry: "./src/index.js", /* 상대경로 */
    output: { /* 절대경로 */
        filename: "[name].js",
        path: path.resolve(__dirname, './dist') /* 현재 프로젝트 디렉토리에서 ../dist에 위치 */
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    plugins: [
        new CleanWebpackPlugin() /* 웹팩 실행시 기존에 있던 번들 파일들을 지워줌 인자에 해당 파일 명을 넣어줘야함 */
    ]
};
/*
entry: 입력파일 경로
output: 출력 디렉토리와 파일 이름
module rules
plugins: 플러그인 등록
*/