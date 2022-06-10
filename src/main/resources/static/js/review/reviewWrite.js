$(document).ready(function() {
	var currentPosition = parseInt($(".quickmenu").css("top"));


	$(window).scroll(function() {
		var position = $(window).scrollTop();
		$(".quickmenu").css("top", position + currentPosition + "px");
	});

	$('#summernote').summernote({
		placeholder: 'Hello Bootstrap 4',
		tabsize: 2,
		minHeight: 500,
		toolbar: [
			// [groupName, [list of button]]
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['insert', ['picture', 'link']],
			['view', ['help']]
		],
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],

		lang: 'ko-KR',
		placeholder: '최대 2048자까지 쓸 수 있습니다.',
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (let i = files.length - 1; i >= 0; i--) {
					sendFile(files[i], this);
				}
			}
		}

	});

	$(".writedetailbtn").on("click", function() {
		$(".writedetail").slideToggle();
	})

	/**
	 * 섬네일 이미지 미리보기
	 */
	function readImage(input) {
		if (input.files && input.files[0]) {
			const reader = new FileReader();

			reader.onload = (e) => {
				const previewImage = document.getElementById('previewImage');
				previewImage.src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);


		}
	}


	// 이벤트 리스너
	document.getElementById('inputImage').addEventListener('change', (e) => {
		readImage(e.target);

	})



	$('.writebtn').click(function() {
		const imageInput = $('#inputImage')[0];
		const title = document.querySelector('.writeTitle');
		// 파일을 여러개 선택할 수 있으므로 files 라는 객체에 담긴다.
		console.log("imageInput: ", imageInput.files)
		if (title.value.trim() < 1) {
			alert("제목을 입력해주세요.");
			return;
		}

		if (imageInput.files.length === 0) {
			alert("대표사진을 넣어주세요.");
			return;
		}


		const formData = new FormData();
		formData.append("file", imageInput.files[0], uuid(imageInput.files[0].name));
		console.log(formData.get('file'))
		$('#rimgadr').val(formData.get('file').name);


		$.ajax({
			type: "POST",
			url: "http://140.238.11.118:5000/upload",
			processData: false,
			contentType: false,
			data: formData,
			success: function(result) {
				console.log("success")
			},
			err: function(err) {
				console.log("err:", err)
			}
		})




		$('#writeinfo').submit();

	})

	// uuid 생성
	function uuid(file_nm) {
		function s4() {
			return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
		}
		return s4() + s4() + s4() + s4() + s4() + s4() + s4() + s4() + file_nm.substr(file_nm.indexOf("."), file_nm.length - 1).toLowerCase();
	}

	// summernote 이미지 저장
	function sendFile(file, el) {
		let formData = new FormData();
		formData.append('file', file, uuid(file.name));
		console.log("uuid 적용확인: ", formData.get('file').name);
		$.ajax({
			type: "POST",
			url: "http://140.238.11.118:5000/upload",
			data: formData,
			processData: false,
			contentType: false,
			success: function(result) {
				console.log("result 값 확인: ", formData.get('file').name);
				$(el).summernote('insertImage', 'http://140.238.11.118:5000/upload/' + result);
				$('#imageBoard > ul').append('<img src="' + result + '" width="auto" height="auto"/>');
				console.log("성공");
			},
			err: function(err) {
				console.log("에러: ", err)
			}
		})
	}

});