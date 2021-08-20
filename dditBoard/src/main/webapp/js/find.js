const idBtn = document.querySelector('.id-btn')
const pwBtn = document.querySelector('.pw-btn')
const idFrm = document.querySelector('.id-frm')
const pwFrm = document.querySelector('.pw-frm')
const idSubmitBtn = document.querySelector('.search-id-btn')
const pwSubmitBtn = document.querySelector('.reset-pw-btn')
const idName = document.querySelector('[name="idName"]')
const idRegno = document.querySelector('[name="idRegno"]') 
const pwId = document.querySelector('[name="pwId"]')
const pwNm = document.querySelector('[name="pwName"]')
const pwRegno = document.querySelector('[name="pwRegno"]')


idBtn.addEventListener('click', e => {
  idSearchClick(e)
})

pwBtn.addEventListener('click', e => {
  pwSearchClick(e)
})

idSubmitBtn.addEventListener('click', e => {
	onIdSubmit(e)
})

pwSubmitBtn.addEventListener('click', e => {
	onPwSubmit(e)
})


function idSearchClick(e) {
  idFrm.classList.add('show')
  pwFrm.classList.remove('show')
  idBtn.classList.add('select-btn')
  pwBtn.classList.remove('select-btn')
}

function pwSearchClick(e) {
  pwFrm.classList.add('show')
  idFrm.classList.remove('show')
  pwBtn.classList.add('select-btn')
  idBtn.classList.remove('select-btn')
}

function onIdSubmit(e) {
	if(idName !== "" && idRegno !== "") {
		idFrm.submit()
	}
}

function onPwSubmit(e) {
	if(pwId !== "" &&
	   pwNm !== "" &&
       pwRegno !== "") {
		pwFrm.submit()
	}
}