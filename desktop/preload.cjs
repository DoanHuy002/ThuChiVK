const {contextBridge,ipcRenderer}=require('electron');
contextBridge.exposeInMainWorld('vk',{
 api:(route,method='GET',body=null,token='')=>ipcRenderer.invoke('api',{route,method,body,token}),
 exportCSV:(name,text)=>ipcRenderer.invoke('export-csv',{name,text}),
 saveBackup:(name,token)=>ipcRenderer.invoke('save-backup',{name,token}),
 restore:(password,token)=>ipcRenderer.invoke('restore',{password,token}),
 importExcel:token=>ipcRenderer.invoke('import-excel',{token}),
 updates:(action,token,url)=>ipcRenderer.invoke('updates',{action,token,url}),
 onUpdate:cb=>{const fn=(_e,s)=>cb(s);ipcRenderer.on('update-state',fn);return()=>ipcRenderer.removeListener('update-state',fn);}
});
