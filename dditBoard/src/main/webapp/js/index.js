const commentBtn = document.querySelectorAll('.comment-btn')
const commentList = document.querySelectorAll('.comment-list')
const modifyBtn = document.querySelector('.user-modify-btn')
const userModifyInfo = document.querySelector('.user-modify-info')
const mobileMenu = document.querySelector('.mobile-device-menu')
const mobileContentsBtn = document.querySelector('.mobile-contents-btn')
const mobileInfoBtn = document.querySelector('.mobile-info-btn')

const userInfo = document.querySelector('.user-info')
const board = document.querySelector('main')

const userId = document.querySelector('.user-name')
const userImg = document.querySelector('.user-img-file')
const modifyImgBtn = document.querySelector('.user-img-btn')
const modifyImgFrm = document.querySelector('.modify-img-frm')

const changePwFrm = document.querySelector('.modify-pw-frm')
const changePwBtn = document.querySelector('.modify-pw-btn')
const changePwInput = document.querySelector('[name="changePw"]')
const pwText = document.querySelector('.pw-text')

const writeFrm = document.querySelector('#write-contents-frm')
const writeArea = document.querySelector('.user-textarea')
const writeTitle = document.querySelector('#write-title')
const writeBtn = document.querySelector('.write-btn')

const boardList = document.querySelector('.board-list')

let pwFlag = false
let scrollFlag = true
let lastBoardNum = 0

for(let i = 0; i < commentBtn.length; i++) {
  commentBtn[i].addEventListener('click', e => {
    showCommentList(e)
  })
}

scrollAjax();

boardList.addEventListener('scroll', e => {
	const top = e.target.scrollTop
	const total = e.target.scrollHeight
	const inner = e.target.offsetHeight
	if(top >= (total - inner) / 2 && scrollFlag) {
		scrollFlag = false
		scrollAjax()
	}
})

writeBtn.addEventListener('click', e => {
	if(writeArea.value === "" && writeTitle.value === '') {
		alert('제목과 내용을 모두 입력해 주세요!')
	} else if(writeArea.value !== "" && writeTitle.value !== "") {
		writeFrm.submit()
	}
})

changePwBtn.addEventListener('click', e => {
	changePw(e)
})

changePwInput.addEventListener('keyup', e=> {
	pwInputCk(e)
})

modifyBtn.addEventListener('click', e => {
  showModify(e)
})

mobileMenu.addEventListener('click', e => {
  mobileMenuClick(e)
})

function showCommentList(e) {
  const list = e.currentTarget.parentNode.nextElementSibling
  list.classList.toggle('show')
}

function showModify(e) {
  userModifyInfo.classList.toggle('show-modify')
}

modifyImgBtn.addEventListener('click', e => {
	doModifyImg(e)
})

function mobileMenuClick(e) {
  const target = e.target
  target.classList.remove('clicked')
  if (target === mobileContentsBtn) {
    userInfo.classList.add('size1024')
    board.classList.remove('display-none')
    mobileInfoBtn.classList.add('clicked')
  } else if (target === mobileInfoBtn) {
    mobileContentsBtn.classList.add('clicked')
    board.classList.add('display-none')
    userInfo.classList.remove('size1024')
  }
}

function doModifyImg(e) {
	const imgFile = userImg.value
	const maxSize = 5 * 1024 * 1024
	
	if(imgFile === "") {
		alert('변경하고싶은 이미지를 첨부해 주세요')
		userImg.focus()
	}
	if(imgFile !== "" && imgFile !== null) {
		if(userImg.files[0].size > maxSize) {
			alert('최대 5MB까지 업로드 가능')
		} else if(!(/\.(gif|jpe?g|tiff?|png|webp|bmp|GIF|JPE?G|TIFF|PNG|WEBP|BMP)$/i).test(imgFile)) {
			alert('이미지 파일만 업로드 가능')
		} else {
			modifyImgFrm.submit()
		}
	}
}

function changePw(e) {
  if(pwFlag) {
		changePwFrm.submit()
	} else {
		alert('알 수 없는 오류 입니다.')
	}
}

function pwInputCk(e) {
	const pwRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}/
	const pwTarget = e.target.value

	if(changePwInput === "") {
		pwText.innerHTML = '8~20자 사이 영어 대소문자, 특수문자 포함'
		pwText.style.color = 'black'
		pwFlag = false
	} else if(!pwRegExp.test(pwTarget)) {
		pwText.innerHTML = '형식에 맞게 다시 입력해 주세요'
		pwText.style.color = 'red'
		pwFlag = false
	} else if(pwRegExp.test(pwTarget)) {
		pwText.innerHTML = '변경 가능한 비밀번호 입니다'
		pwText.style.color = 'black'
		pwFlag = true
	}
}

function scrollAjax() {
	$.ajax({
			type: 'post',
			url: '/LoadPage',
			data: {
				loadedRow: lastBoardNum
			},
			dataType: 'json',
			success: function(result) {
				const res = result
				if(res !== null) {
					loadPage(res)
					lastBoardNum += 10;
				}
			}
		})
}
function loadPage(res) {
	const resultSet = res
	const resLength = Object.keys(res).length
	for(let i = 0; i < resLength; i++) {
		const rsBoardIdx = resultSet[i].boardIdx
		const rsBoardTitle = resultSet[i].boardTitle
		const rsBoardContent = resultSet[i].boardContent
		const rsBoardDate = resultSet[i].boardDate
		const rsBoardAnon = resultSet[i].boardAnon
		let rsBoardUserImg = resultSet[i].boardUserImg
		const rsMemId = resultSet[i].memId
		const rsCommCnt = resultSet[i].commCnt
		const rsBoardUserName = resultSet[i].boardUserName
		if(rsBoardUserImg === undefined) {
			rsBoardUserImg = 'defaultImg.png'
		}
		
		const cardLi = document.createElement('li')
		cardLi.setAttribute('class', 'card')
		
			const cardImg = document.createElement('img')
			cardImg.setAttribute('class', 'user-img')
		if(rsBoardAnon === 'Y') {
			cardImg.setAttribute('src', `../storage/anon.jpg`)
		} else if(rsBoardAnon === 'N' || rsMemId === 'admin'){
			cardImg.setAttribute('src', `../storage/${rsBoardUserImg}`)
		}
			cardImg.setAttribute('alt', 'userPhoto')						
		
		const cardWrapper = document.createElement('div')
		cardWrapper.setAttribute('class', 'wrapper')
		
		const cardAuthor = document.createElement('div')
		cardAuthor.setAttribute('class', 'author')
		
		const authorSpan = document.createElement('span')
		if(rsBoardAnon === 'Y') {
			authorSpan.append(`익명`)
		} else if(rsBoardAnon === 'N' || rsMemId === 'admin'){
			authorSpan.append(`${rsBoardUserName}`)
		}
		
		const deleteBtn = document.createElement('a')
		deleteBtn.setAttribute('href', `/board?cmd=boardDelete&boardNum=${rsBoardIdx}`)
		deleteBtn.append('삭제')
		
		const cardTitle = document.createElement('div')
		cardTitle.setAttribute('class', 'title')
		cardTitle.append(`${rsBoardTitle}`)
		
		const cardDate = document.createElement('div')
		cardDate.setAttribute('class', 'write-date')
		cardDate.append(`${rsBoardDate}`)
		
		const cardContent = document.createElement('div')
		cardContent.setAttribute('class', 'content')
		cardContent.append(`${rsBoardContent}`)
		
		cardAuthor.appendChild(authorSpan)
		cardWrapper.appendChild(cardAuthor)
		cardWrapper.appendChild(cardTitle)
		cardWrapper.appendChild(cardDate)
		cardWrapper.appendChild(cardContent)
		
		const cardBtnWrap = document.createElement('div')
		cardBtnWrap.setAttribute('class', 'card-btn')
		const cardBtn = document.createElement('button')
		cardBtn.setAttribute('class', 'comment-btn')
		cardBtn.addEventListener('click', e => {
			showCommentList(e)
		})
		const cardIcon = document.createElement('i')
		cardIcon.setAttribute('class', 'fa fa-commenting-o')
		cardBtn.appendChild(cardIcon)
		cardBtn.append(`${rsCommCnt}`)
		cardBtnWrap.appendChild(cardBtn)
		
		const comContainer = document.createElement('ul')
		comContainer.setAttribute('class', 'comment-list')
		
		for(let j = 0; j < rsCommCnt; j++) {
			const rsComBoardIdx = resultSet[i].commList[j].comBoardIdx
			const rsComIdx = resultSet[i].commList[j].comIdx
			const rsComMemId = resultSet[i].commList[j].comMemId
			const rsComContent = resultSet[i].commList[j].comContent
			const rsComDate = resultSet[i].commList[j].comDate
			const rsComAnon = resultSet[i].commList[j].comAnon
			const rsComState = resultSet[i].commList[j].commState
			const rsComUserName = resultSet[i].commList[j].commUserName
			
			const comLi = document.createElement('li')
			comLi.setAttribute('class', 'comment')
			
			const comDesc = document.createElement('p')
			comDesc.setAttribute('class', 'description')
			comDesc.append(`${rsComContent}`)
			
			const comAuthor = document.createElement('span')
			comAuthor.setAttribute('class', 'comment-author')
			if(rsComAnon === 'Y') {
				comAuthor.append(' - 익명')
			} else {
				comAuthor.append(` - ${rsComUserName}`)				
			}
			
			if(rsComState === 'Y') {
				comDesc.appendChild(comAuthor)
			}
			comLi.appendChild(comDesc)
			comContainer.appendChild(comLi)
			
			if(resultSet[i].commList[j].commentUser != undefined) {
				const comDelBtn = document.createElement('a')
				comDelBtn.setAttribute('href', `/board?cmd=commentDelete&number=${rsComIdx}&board=${rsComBoardIdx}`)
				comDelBtn.setAttribute('class', 'com-del')
				comDelBtn.append(' 삭제')
				comAuthor.appendChild(comDelBtn)
			}
		}
		
		const comInsertBox = document.createElement('li')
		comInsertBox.setAttribute('class', 'insert-box')
		
		const comInsertFrm = document.createElement('form')
		comInsertFrm.setAttribute('class', 'insert-comment')
		comInsertFrm.setAttribute('action', '/board?cmd=commentPro')
		comInsertFrm.setAttribute('name', 'commFrm')
		comInsertFrm.setAttribute('method', 'post')
		
		const comTextArea = document.createElement('textarea')
		comTextArea.setAttribute('class', 'comment-text-area')
		comTextArea.setAttribute('name', 'commArea')
		
		const comAnonBtn = document.createElement('div')
		comAnonBtn.setAttribute('class', 'btn-check-wrap')
		
		const comAndBoardIdx = document.createElement('input')
		comAndBoardIdx.setAttribute('type', 'hidden')
		comAndBoardIdx.setAttribute('value', `${rsBoardIdx}`)
		
		const comAnonLabel = document.createElement('label')
		comAnonLabel.setAttribute('for', 'comment-anonymity')
		comAnonLabel.append('익명')
		
		const comHiddenInput = document.createElement('input')
		comHiddenInput.setAttribute('type', 'hidden')
		comHiddenInput.setAttribute('value', `${rsBoardIdx}`)
		comHiddenInput.setAttribute('name', 'comInBoard')
		
		const comInput = document.createElement('input')
		comInput.setAttribute('id', 'comment-anonymity')
		comInput.setAttribute('type', 'checkbox')
		comInput.setAttribute('name', 'commAnon')
		
		const comSubmitBtn = document.createElement('button')
		comSubmitBtn.setAttribute('class', 'insert-comment-btn')
		comSubmitBtn.append('댓글 등록')
		
		if(resultSet[i].boardUser != undefined) {
			cardAuthor.appendChild(deleteBtn)
		}
		
		comAnonBtn.appendChild(comAndBoardIdx)
		comAnonBtn.appendChild(comAnonLabel)
		comAnonBtn.appendChild(comInput)
		comInsertFrm.appendChild(comHiddenInput)
		comInsertFrm.appendChild(comTextArea)
		comInsertFrm.appendChild(comAnonBtn)
		comInsertFrm.appendChild(comSubmitBtn)
		comInsertBox.appendChild(comInsertFrm)
		comContainer.appendChild(comInsertBox)
		
		cardLi.appendChild(cardImg)
		cardLi.appendChild(cardWrapper)
		cardLi.appendChild(cardBtnWrap)
		//cardLi.appendChild(commentList)
		cardLi.appendChild(comContainer)
		boardList.appendChild(cardLi)
	}
	scrollFlag = true;
}










