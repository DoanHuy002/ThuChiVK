const config=require('./package.json').build;
if(process.env.VK_UPDATE_URL){const url=new URL(process.env.VK_UPDATE_URL);if(url.protocol!=='https:')throw Error('VK_UPDATE_URL must use HTTPS');config.publish=[{provider:'generic',url:url.toString()}];}
module.exports=config;
