import React,{useState} from 'react';
export default function PasswordInput({label,name,value,onChange,required}){
 const [visible,setVisible]=useState(false);
 return <span className="password-control"><input name={name} type={visible?'text':'password'} value={value??''} onChange={e=>onChange(name,e.target.value)} required={required} minLength={1} maxLength={72} autoComplete={name==='oldPassword'?'current-password':undefined}/><button type="button" aria-label={(visible?'Ẩn ':'Hiện ')+label.toLocaleLowerCase('vi')} aria-pressed={visible} title={visible?'Ẩn mật khẩu':'Hiện mật khẩu'} onMouseDown={e=>e.preventDefault()} onClick={()=>setVisible(v=>!v)}><svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" strokeWidth="1.8" aria-hidden="true"><path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12Z"/><circle cx="12" cy="12" r="3"/>{visible&&<path d="M3 3l18 18"/>}</svg></button></span>;
}
