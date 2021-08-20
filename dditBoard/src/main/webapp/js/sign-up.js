//Document
const frm = document.querySelector('.sign-up-frm')
const userId = document.querySelector('#user-id')
const userIdText = document.querySelector('.user-id-wranning')
const userIdCheckBtn = document.querySelector('.id-check-btn')
const userPw = document.querySelector('#user-pw')
const userPwText = document.querySelector('.user-pw-wranning')
const userPwCk = document.querySelector('#user-pw-check')
const userPwCkText = document.querySelector('.user-pw-ck-wranning')
const userName = document.querySelector('#user-name')
const userEmail = document.querySelector('#user-email')
const sendLisence = document.querySelector('.send-lisence')
const checkLisence = document.querySelector('.check-lisence')
const lisenceBtn = document.querySelector('.email-btn')
const lisenceNumber = document.querySelector('.lisence')
const signBtn = document.querySelector('.frm-submit-btn')



//valiable
let idCk = false
let pwCk = false
let nameCk = false
let emailCk = false
let lisenceCk = false

// create EventListener
//userId.addEventListener('keyup', e => {
//  idRegExrCheck(e)
//  duplicateCheck(e)
//})

userPw.addEventListener('keyup', e => {
  pwRegExrCheck(e)
})

userPwCk.addEventListener('keyup', e => {
  pwCheck(e)
})

userName.addEventListener('keyup', e => {
  nameCheck(e)
})

sendLisence.addEventListener('click', e => {
  sendEmail(e)
})

checkLisence.addEventListener('click', e => {
  matchLisence(e)
})

frm.addEventListener('keyup', e => {
  signFunc(e)
})

signBtn.addEventListener('click', () => {
  frm.submit()
})

// create Function
//function idRegExrCheck(e) {
//  const idRegExp = /^[a-zA-z0-9]{6,20}$/
//  const idTarget = e.target.value
//  if(idTarget === "") {
//    userIdCheckBtn.disabled = true
//    userIdText.innerHTML = '6~20자 사이로 영어, 숫자만 사용 가능하며 특수문자는 _(언더바)만 가능합니다.'
//    userIdText.style.color = 'black'
//  } else if(idRegExp.test(idTarget)) {
//    userIdCheckBtn.disabled = false
//    userIdText.innerHTML = '사용 가능, 중복체크 해주세요'
//    userIdText.style.color = 'black'
//    idCk = true
//  } else {
//    userIdCheckBtn.disabled = true
//    userIdText.innerHTML = '형식에 맞게 다시 입력해 주세요'
//    userIdText.style.color = 'red'
//  }
//}

//function duplicateCheck(e) {
//  if(userIdCheckBtn.disabled === false) {
//    // 아이디 중복 체크
//  }
//}

function pwRegExrCheck(e) {
  const pwRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!#%*?&])[A-Za-z\d$@$#!%*?&]{8,20}/
  const pwTarget = e.target.value
  if(pwTarget === "") {
    userPwText.innerHTML = "영어 대소문자, 숫자, 특수문자를 사용하여 8~20자 사이로 입력해 주세요"
    userPwCkText.style.color = 'black'
    userPwCk.disabled = true
    pwCk = false
    userPwCk.value = ""
  } else if(pwRegExp.test(pwTarget)) {
    userPwText.innerHTML = "아래 비밀번호 확인 창에 한번 더 입력해 주세요"
    userPwText.style.color = 'black'
    userPwCk.disabled = false
    pwCk = false
  } else {
    userPwText.innerHTML = "형식에 맞게 다시 입력해 주세요"
    userPwText.style.color = 'red'
    userPwCk.disabled = true
    pwCk = false
  }
}

function pwCheck(e) {
  const current = userPw.value
  const targetPw = e.target.value
  if(current === targetPw) {
    userPwCkText.innerHTML = "일치합니다."
    userPwCkText.style.color = 'black'
    pwCk = true
  } else {
    userPwCkText.innerHTML = "일치하지 않습니다."
    userPwCkText.style.color = 'red'
    pwCk = false
  }
}

function nameCheck(e) {
  const nameReg = /^[가-힣]{2,10}$/
  const name = e.target.value
  if(!nameReg.test(name)) {
    e.target.style.borderColor = 'red'
    nameCk = false
  } else {
    e.target.style.borderColor = 'black'
    nameCk = true
  }
}

function sendEmail(e) {
  const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  const email = userEmail.value
  if(reg.test(email)) {
    userEmail.readOnly = true
    sendLisence.disabled = true
    userEmail.style.borderColor = 'black'
  } else {
    userEmail.readOnly = false
    sendLisence.disabled = false
    userEmail.style.borderColor = 'red'
  }
}

function matchLisence(e) {
  if(lisenceNumber.value !== "") {
    lisenceCk = true
    emailCk = true
    checkLisence.disabled = true
    lisenceNumber.disabled = true
    signFunc()
  } else {
    checkLisence.disabled = false
    lisenceNumber.disabled = false
  }
}

function signFunc(e) {
  if(pwCk && nameCk && emailCk && lisenceCk) {
    signBtn.disabled = false
  } else {
    signBtn.disabled = true
  }
}