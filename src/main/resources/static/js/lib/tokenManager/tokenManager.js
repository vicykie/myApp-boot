var tokenManager = (function () {
    var tokenHeader = "X-AUTH-TOKEN";

    function setToken(token) {
        localStorage.setItem(tokenHeader, token);
    }

    function getToken() {
        //HTTP headers don't understand null
        return localStorage.getItem(tokenHeader) != null ?
            localStorage.getItem(tokenHeader) : "";
    }

    function deleteToken() {
        localStorage.removeItem(tokenHeader);
    }

    return {
        setToken: setToken,
        getToken: getToken,
        deleteToken: deleteToken,
        tokenHeader: tokenHeader
    };
})();