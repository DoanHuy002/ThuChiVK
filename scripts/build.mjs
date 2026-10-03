import {build} from 'esbuild';import fs from 'node:fs/promises';
await fs.mkdir('dist/assets',{recursive:true});
await build({entryPoints:['src/main.jsx'],bundle:true,outdir:'dist/assets',minify:true,define:{'process.env.NODE_ENV':JSON.stringify('production')},jsx:'automatic',target:'chrome140'});
await fs.copyFile('public/logo.jpg','dist/logo.jpg');
let html=await fs.readFile('index.html','utf8');html=html.replace('<script type="module" src="/src/main.jsx"></script>','<link rel="stylesheet" href="./assets/main.css"/><script type="module" src="./assets/main.js"></script>');await fs.writeFile('dist/index.html',html);console.log('React production build completed');
