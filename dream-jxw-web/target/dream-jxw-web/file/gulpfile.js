var gulp = require('gulp');

// 公用util
var browserSync = require('browser-sync'); // 自动刷新
var plumber = require('gulp-plumber'); // gulp 错误处理
var rename = require('gulp-rename'); // 文件重命名

// CSS相关
var postcss = require('gulp-postcss');
var precss = require('precss');
var autoprefixer = require('autoprefixer'); // 解析CSS文件并添加浏览器前缀到CSS规则里
// cssnano插件可以用来压缩CSS  var cssnano = require('cssnano');
var extend = require('postcss-simple-extend');
var mixins = require('postcss-sassy-mixins');
var comment = require('postcss-inline-comment');
var scss = require('postcss-scss');
var stripInlineComments = require('postcss-strip-inline-comments');

// build 使用
var runSequence = require('run-sequence'); // 另一个任务同步处理工具
var del = require('del'); // 删除文件专用
var zip = require('gulp-zip'); // 导出zip文件

function buildCss() {
    var postCssPlugins = [
        precss,
        autoprefixer({
            browsers: ['> 1%', 'IE > 8', 'Android >= 1.6', 'iOS >= 1.0']
        }),
        stripInlineComments
    ];

    gulp.src('./scss/style.scss')
        .pipe(plumber())
        .pipe(postcss(postCssPlugins, {syntax: scss}))
        .pipe(rename(function (path) {
            path.extname = '.css';
        }))
        .pipe(gulp.dest('./css'));
}

gulp.task('browserSync', function () {
    browserSync.init({
        port: 2333,
        server: {
            baseDir: './',
            index: 'index.html'
        }
    });

    browserSync.watch('./css/*.css').on('change', browserSync.reload);
    browserSync.watch('./js/*.js').on('change', browserSync.reload);
    browserSync.watch('./*.html').on('change', browserSync.reload);
});

gulp.task('scss', function () {
    buildCss();
    gulp.watch('scss/*.scss', function () {
        buildCss();
    });
});

gulp.task('default', ['browserSync', 'scss']);

// ---------- 下面是编译所有的 gulp处理工具 --------- 下面的先不看还没有完成

// 清空build目录
gulp.task('clean', function (cb) {
    return del([
        './dist/**/*'
    ], cb);
});

// 只编译css 不监听
gulp.task('css:build', function () {
    buildCss();
});

// 移动img html js css
gulp.task('file:copy', function () {
    // 复制 html
    gulp.src('./*.html')
        .pipe(gulp.dest('./dist'));

    // 复制 js
    gulp.src(['./js/*.js', './js/**/*'])
        .pipe(gulp.dest('./dist/js'));

    // 复制 img
    gulp.src(['./img/*', './img/**/*'])
        .pipe(gulp.dest('./dist/img'));

    // 复制 css
    gulp.src('./css/*.css')
        .pipe(gulp.dest('./dist/css'));
});

gulp.task('zip', function () {
    gulp.src(['./dist/*', './dist/**/*'])
        .pipe(zip('sae.zip'))
        .pipe(gulp.dest('./dist'));
});

gulp.task('build', function () {
    // 复制 html
    runSequence('clean', ['css:build'], 'file:copy', function () {
        console.log('build boom!');
    });
});
