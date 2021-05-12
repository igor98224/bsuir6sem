const enter = document.querySelector(".enter");
const register = document.querySelector(".register");

const signIn = document.querySelector(".sign-in");
const signUp = document.querySelector(".sign-up");

//const player = document.querySelector("#player");

enter.addEventListener("click", showSignIn);

register.addEventListener("click", showSignUp);

document.querySelector("#one-btn").addEventListener('click', savetLocalStorage)
document.querySelector("#two-btn").addEventListener('click', savetLocalStorage)

function savetLocalStorage(e){
    e.preventDefault();
    if(e.target===document.querySelector("#one-btn")){

        localStorage.setItem('login',document.querySelector('#em').value)
        document.forms[0].submit()
    }else if(e.target===document.querySelector("#two-btn")){

        localStorage.setItem('login',document.querySelector('#email').value)
        document.forms[1].submit()
    }
}

function showSignIn(){
    signUp.style.display="none";
    signIn.style.display="block";
    player.style.bottom = "100";
}

function showSignUp(){
    signIn.style.display="none";
    signUp.style.display="block";
    player.style.top = "100";
}
