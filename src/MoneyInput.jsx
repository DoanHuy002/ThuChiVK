import React,{useLayoutEffect,useRef} from 'react';
export default function MoneyInput({name,value,onChange,required=false}){
 const input=useRef(null),caret=useRef(null);
 const format=v=>{const s=String(v??'').replace(/\./g,'');if(!s||s==='-')return s;const negative=s.startsWith('-');return (negative?'-':'')+s.replace(/^-/, '').replace(/^0+(?=\d)/,'').replace(/\B(?=(\d{3})+(?!\d))/g,'.');};
 useLayoutEffect(()=>{if(caret.current!==null&&input.current){input.current.setSelectionRange(caret.current,caret.current);caret.current=null;}},[value]);
 return <input ref={input} name={name} type="text" inputMode="numeric" required={required} value={format(value)} onChange={e=>{const raw=e.target.value;if(!/^-?[\d.]*$/.test(raw))return;const left=raw.slice(0,e.target.selectionStart).replace(/\./g,'').length;const clean=raw.replace(/\./g,'');if(clean.replace('-','').length>13)return;const display=format(clean);let pos=0,count=0;while(pos<display.length&&count<left){if(display[pos]!=='.')count++;pos++;}caret.current=pos;onChange(name,clean);}}/>;
}
