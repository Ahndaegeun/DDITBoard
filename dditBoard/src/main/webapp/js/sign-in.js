const frm = document.querySelector('.login-frm')
const userId = document.querySelector('.user-id')
const userPw = document.querySelector('.user-pw')
//const rememberCk = document.querySelector('.re-check')
const frmBtn = document.querySelector('.login-btn')

//Default Set
userId.autocomplete = false
userPw.autocomplete = false
//rememberCk.checked = false

//EVENT
window.addEventListener('load', () => {
  if(document.cookie) {
    userId.value = document.cookie
    //rememberCk.checked = true
  }
})

frmBtn.addEventListener('click', () => {
  pwCheck()
  idCheck()
  onLogin()
  //rememberId()
})

frm.addEventListener('keyup', e => {
	if(e.keyCode === 13) {
		frm.submit()
	}
})

//Event Function
function onLogin() {
  if (userId.value !== "" && userPw.value !== "") {
    frm.submit()
  } else {
    if(!userId.value && !userPw.value) alert('ID, PW 입력')
    else if(!userId.value) alert('ID입력')
    else if(!userPw.value) alert('PW입력')
  }
}

function rememberId() {
  if(rememberCk.checked && userId.value) {
    document.cookie = userId.value
  }
}

function idCheck() {
  //길이 4~20
  //숫자로 시작 X
  //한글 X
  //대문자X
  const str = userId.value;
  const reg = /[a-z]{4,20}|[0-9]{4,20}/;
  if(reg.test(str)) {
    //console.log('아이디는 4~20, 숫자로 시작X, 한글X')
  }
}

function pwCheck() {
  //길이 8~20
  const len = userPw.value.length
  if(!(len < 8)) {
    //console.log('비밀번호 짧아');
    return false
  } else if(!(len <= 20)) {
    //console.log('비밀번호 길이 초과')
    return false
  }
  return true
}