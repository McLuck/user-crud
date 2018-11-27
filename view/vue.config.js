module.exports = {
    css: {
       sourceMap: true,
    },
    //baseUrl: '/',
    outputDir: '../src/main/resources/static',
    configureWebpack: {
        performance: {
            maxAssetSize: 500000,
        },
    }
};