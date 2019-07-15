function get_history_length() {
    document.getElementById("ContentPlaceHolderSide_txtFromClient").value = history.length
}

function show_message(n) {
    alert(n)
}

function show_eventi() {
    return alert(sEventi), !1
}

function confirm_message(n, t, i) {
    return bSave ? t ? !confirm(n) : (show_message(n), !1) : (isNaN(i) || i ? ApplyWait() : document.getElementById("ContentPlaceHolderBody_ButtonSalva") && (document.getElementById("ContentPlaceHolderBody_ButtonSalva").disabled = !0), !0)
}

function confirm_to_close(n) {
    alert(n);
    window.close()
}

function setSaveFlag(n) {
    bSave = n
}

function setBackFlag(n) {
    bBackActive = n
}

function setEventi(n) {
    sEventi = n
}

function checkInputYesOrNot(n, t) {
    t.value != "S" && t.value != "N" && (t.message = "Errore: inserire 'S' o 'N'", t.cancel = !0)
}

function adviseOnBeforeUnload() {
    if (bSave) return "I dati non ancora salvati non saranno registrati."
}

function setValue(n, t) {
    var i = document.getElementById(n);
    i ? i.value = t : alert("null")
}

function validateVoteSCR(n) {
    var i = n.value, t = i.lastIndexOf("/");
    t < 0 && (t = i.lastIndexOf("\\"));
    t >= 0 && (n.message = "1 - Attenzione il campo non puï¿½ contenere il carattere barra", n.cancel = !0)
}

function validateVoteRED(n) {
    var i = n.value, t = i.lastIndexOf("/");
    t < 0 && (t = i.lastIndexOf("\\"));
    t >= 0 ? (n.message = "1 - Attenzione il campo non puï¿½ contenere il carattere barra", n.cancel = !0) : (t = sEventi.lastIndexOf(i), sEventi.length > 0 && t < 0 && (n.message = "2 - Attenzione il dato inserito non ï¿½ valido, controllare e modificare", n.cancel = !0))
}

function validateTime(n) {
    var t = n.value, e = ".", i = t.lastIndexOf(e), r, u, f;
    i >= 3 && t.length > i ? (t.length - 1 - i == 1 ? t.substr(i + 1, 1) != "0" && (n.message = "1 - Attenzione non inserire secondi. Esempio: 8.15.0", n.cancel = !0) : t.length - 1 - i == 2 ? t.substr(i + 1, 2) != "00" && (n.message = "2 - Attenzione non inserire secondi. Esempio: 08.15.00", n.cancel = !0) : (n.message = "Attenzione parte secondi errata.", n.cancel = !0), r = t.indexOf(e), r == 1 || r == 2 ? (u = t.substring(0, r), f = t.substring(r + 1, i), (u < 0 || u > 23) && (n.message = "Attenzione parte ore errata.", n.cancel = !0), (f < 0 || f > 59) && (n.message = "Attenzione parte minuti errata.", n.cancel = !0)) : (n.message = "Attenzione parte ore:minuti errata.", n.cancel = !0)) : (n.message = "Attenzione valore errato. Inserire un ora nel formato oo.mm.00 oppure o.m.00. Esempio: 7.15.00", n.cancel = !0)
}

function validateDate(n, t) {
    var u, f, r, i = n.value,
        a = /\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/.test(i) || /\b\d{1,2}[\/-]\d{1,2}[\/-]\d{2}\b/.test(i), e;
    if (t && i.lenght == 0) return alert("Attenzione campo Data obbligatorio."), !1;
    if (a) {
        var s = i.indexOf("/") != -1 ? "/" : "-", h = i.indexOf(s), c = i.lastIndexOf(s);
        if (f = parseInt(i.substring(0, h), 10), u = parseInt(i.substring(h + 1, c), 10), r = parseInt(i.substring(c + 1), 10), r < 100) {
            var l = new Date, o = parseInt(l.getFullYear() / 100) * 100, v = l.getFullYear() + 15 - o;
            r += r > v ? o - 100 : o
        }
        if (e = new Date(r, u - 1, f), e.getDate() == f) if (e.getMonth() + 1 == u) {
            if (e.getFullYear() == r) return n.value = f + "/" + u + "/" + r, !0;
            alert("Attenzione controllare Anno immesso.")
        } else alert("Attenzione controllare Mese immesso."); else alert("Attenzione controllare Data immessa.")
    } else alert("Attenzione formato errato. Inserire data nel formato gg/mm/aaaa.");
    return !1
}

function test_popup() {
    sPopupEnable == "Start" && (sPopupEnable = "False")
}

function alert_popup() {
    sPopupEnable == "False" && alert("Attenzione i Pop-Up risultano bloccati!\n\nQualunque browser usiate verificate di avere abilitate le 'pagine di pop-up', questa avvertenza vale anche se utilizzate Tool-bar insieme al browser (es. Gooogle Toolbar).\nConsultare, per approfondimenti, la guida relativa al proprio browser scaricabile cliccando sui link a fondo pagina.")
}

function beep() {
    var n = new Audio("data:audio/wav;base64,//uQRAAAAWMSLwUIYAAsYkXgoQwAEaYLWfkWgAI0wWs/ItAAAGDgYtAgAyN+QWaAAihwMWm4G8QQRDiMcCBcH3Cc+CDv/7xA4Tvh9Rz/y8QADBwMWgQAZG/ILNAARQ4GLTcDeIIIhxGOBAuD7hOfBB3/94gcJ3w+o5/5eIAIAAAVwWgQAVQ2ORaIQwEMAJiDg95G4nQL7mQVWI6GwRcfsZAcsKkJvxgxEjzFUgfHoSQ9Qq7KNwqHwuB13MA4a1q/DmBrHgPcmjiGoh//EwC5nGPEmS4RcfkVKOhJf+WOgoxJclFz3kgn//dBA+ya1GhurNn8zb//9NNutNuhz31f////9vt///z+IdAEAAAK4LQIAKobHItEIYCGAExBwe8jcToF9zIKrEdDYIuP2MgOWFSE34wYiR5iqQPj0JIeoVdlG4VD4XA67mAcNa1fhzA1jwHuTRxDUQ//iYBczjHiTJcIuPyKlHQkv/LHQUYkuSi57yQT//uggfZNajQ3Vmz+Zt//+mm3Wm3Q576v////+32///5/EOgAAADVghQAAAAA//uQZAUAB1WI0PZugAAAAAoQwAAAEk3nRd2qAAAAACiDgAAAAAAABCqEEQRLCgwpBGMlJkIz8jKhGvj4k6jzRnqasNKIeoh5gI7BJaC1A1AoNBjJgbyApVS4IDlZgDU5WUAxEKDNmmALHzZp0Fkz1FMTmGFl1FMEyodIavcCAUHDWrKAIA4aa2oCgILEBupZgHvAhEBcZ6joQBxS76AgccrFlczBvKLC0QI2cBoCFvfTDAo7eoOQInqDPBtvrDEZBNYN5xwNwxQRfw8ZQ5wQVLvO8OYU+mHvFLlDh05Mdg7BT6YrRPpCBznMB2r//xKJjyyOh+cImr2/4doscwD6neZjuZR4AgAABYAAAABy1xcdQtxYBYYZdifkUDgzzXaXn98Z0oi9ILU5mBjFANmRwlVJ3/6jYDAmxaiDG3/6xjQQCCKkRb/6kg/wW+kSJ5//rLobkLSiKmqP/0ikJuDaSaSf/6JiLYLEYnW/+kXg1WRVJL/9EmQ1YZIsv/6Qzwy5qk7/+tEU0nkls3/zIUMPKNX/6yZLf+kFgAfgGyLFAUwY//uQZAUABcd5UiNPVXAAAApAAAAAE0VZQKw9ISAAACgAAAAAVQIygIElVrFkBS+Jhi+EAuu+lKAkYUEIsmEAEoMeDmCETMvfSHTGkF5RWH7kz/ESHWPAq/kcCRhqBtMdokPdM7vil7RG98A2sc7zO6ZvTdM7pmOUAZTnJW+NXxqmd41dqJ6mLTXxrPpnV8avaIf5SvL7pndPvPpndJR9Kuu8fePvuiuhorgWjp7Mf/PRjxcFCPDkW31srioCExivv9lcwKEaHsf/7ow2Fl1T/9RkXgEhYElAoCLFtMArxwivDJJ+bR1HTKJdlEoTELCIqgEwVGSQ+hIm0NbK8WXcTEI0UPoa2NbG4y2K00JEWbZavJXkYaqo9CRHS55FcZTjKEk3NKoCYUnSQ0rWxrZbFKbKIhOKPZe1cJKzZSaQrIyULHDZmV5K4xySsDRKWOruanGtjLJXFEmwaIbDLX0hIPBUQPVFVkQkDoUNfSoDgQGKPekoxeGzA4DUvnn4bxzcZrtJyipKfPNy5w+9lnXwgqsiyHNeSVpemw4bWb9psYeq//uQZBoABQt4yMVxYAIAAAkQoAAAHvYpL5m6AAgAACXDAAAAD59jblTirQe9upFsmZbpMudy7Lz1X1DYsxOOSWpfPqNX2WqktK0DMvuGwlbNj44TleLPQ+Gsfb+GOWOKJoIrWb3cIMeeON6lz2umTqMXV8Mj30yWPpjoSa9ujK8SyeJP5y5mOW1D6hvLepeveEAEDo0mgCRClOEgANv3B9a6fikgUSu/DmAMATrGx7nng5p5iimPNZsfQLYB2sDLIkzRKZOHGAaUyDcpFBSLG9MCQALgAIgQs2YunOszLSAyQYPVC2YdGGeHD2dTdJk1pAHGAWDjnkcLKFymS3RQZTInzySoBwMG0QueC3gMsCEYxUqlrcxK6k1LQQcsmyYeQPdC2YfuGPASCBkcVMQQqpVJshui1tkXQJQV0OXGAZMXSOEEBRirXbVRQW7ugq7IM7rPWSZyDlM3IuNEkxzCOJ0ny2ThNkyRai1b6ev//3dzNGzNb//4uAvHT5sURcZCFcuKLhOFs8mLAAEAt4UWAAIABAAAAAB4qbHo0tIjVkUU//uQZAwABfSFz3ZqQAAAAAngwAAAE1HjMp2qAAAAACZDgAAAD5UkTE1UgZEUExqYynN1qZvqIOREEFmBcJQkwdxiFtw0qEOkGYfRDifBui9MQg4QAHAqWtAWHoCxu1Yf4VfWLPIM2mHDFsbQEVGwyqQoQcwnfHeIkNt9YnkiaS1oizycqJrx4KOQjahZxWbcZgztj2c49nKmkId44S71j0c8eV9yDK6uPRzx5X18eDvjvQ6yKo9ZSS6l//8elePK/Lf//IInrOF/FvDoADYAGBMGb7FtErm5MXMlmPAJQVgWta7Zx2go+8xJ0UiCb8LHHdftWyLJE0QIAIsI+UbXu67dZMjmgDGCGl1H+vpF4NSDckSIkk7Vd+sxEhBQMRU8j/12UIRhzSaUdQ+rQU5kGeFxm+hb1oh6pWWmv3uvmReDl0UnvtapVaIzo1jZbf/pD6ElLqSX+rUmOQNpJFa/r+sa4e/pBlAABoAAAAA3CUgShLdGIxsY7AUABPRrgCABdDuQ5GC7DqPQCgbbJUAoRSUj+NIEig0YfyWUho1VBBBA//uQZB4ABZx5zfMakeAAAAmwAAAAF5F3P0w9GtAAACfAAAAAwLhMDmAYWMgVEG1U0FIGCBgXBXAtfMH10000EEEEEECUBYln03TTTdNBDZopopYvrTTdNa325mImNg3TTPV9q3pmY0xoO6bv3r00y+IDGid/9aaaZTGMuj9mpu9Mpio1dXrr5HERTZSmqU36A3CumzN/9Robv/Xx4v9ijkSRSNLQhAWumap82WRSBUqXStV/YcS+XVLnSS+WLDroqArFkMEsAS+eWmrUzrO0oEmE40RlMZ5+ODIkAyKAGUwZ3mVKmcamcJnMW26MRPgUw6j+LkhyHGVGYjSUUKNpuJUQoOIAyDvEyG8S5yfK6dhZc0Tx1KI/gviKL6qvvFs1+bWtaz58uUNnryq6kt5RzOCkPWlVqVX2a/EEBUdU1KrXLf40GoiiFXK///qpoiDXrOgqDR38JB0bw7SoL+ZB9o1RCkQjQ2CBYZKd/+VJxZRRZlqSkKiws0WFxUyCwsKiMy7hUVFhIaCrNQsKkTIsLivwKKigsj8XYlwt/WKi2N4d//uQRCSAAjURNIHpMZBGYiaQPSYyAAABLAAAAAAAACWAAAAApUF/Mg+0aohSIRobBAsMlO//Kk4soosy1JSFRYWaLC4qZBYWFRGZdwqKiwkNBVmoWFSJkWFxX4FFRQWR+LsS4W/rFRb/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VEFHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAU291bmRib3kuZGUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAwNGh0dHA6Ly93d3cuc291bmRib3kuZGUAAAAAAAAAACU=");
    n.play()
}

function isNumberKey(n) {
    var t = n.which ? n.which : event.keyCode;
    return t > 31 && (t < 48 || t > 57) ? (beep(), !1) : !0
}

function ShowHideComunicazioni(n) {
    n.document.getElementById("divComunicazioni").style.display != "none" ? n.document.getElementById("divComunicazioni").style.display = "none" : (setPosition("divComunicazioni", 0, 0), n.document.getElementById("divComunicazioni").style.display = "")
}

function ShowHelp(n, t) {
    var r, u, i = 0;
    switch (n) {
        case"":
        case"0":
            t == "" ? r = "1" - i : t == "RE_Help_Login" ? r = "7" - i : t == "RE_Help_ChangePassword" ? r = "11" - i : t == "RE_Help_Improve" && (r = "12" - i);
            u = "../QG/QG_RE_Login.pdf?#zoom=85&scrollbar=1&toolbar=0&navpanes=1&page=";
            $("#pdfcontent").attr("src", u + r);
            $("#RE_HELP").modal();
            break;
        case"1":
        case"3":
            r = t == "" ? "1" - i : t == "RE_Help_Family_MENU" ? "8" - i : t == "RE_Help_Family" ? "5" - i : t == "RE_Help_Family_ANA" ? "10" - i : t == "RE_Help_Family_CUR" ? "11" - i : t == "RE_Help_Family_ASS" ? "12" - i : t == "RE_Help_Family_VOT" ? "15" - i : t == "RE_Help_Family_RED" ? "16" - i : t == "RE_Help_Family_REC" ? "19" - i : t == "RE_Help_Family_PC" ? "20" - i : t == "RE_Help_Family_COM" ? "9" - i : t == "RE_Help_Family_PER" ? "13" - i : "8" - i;
            u = "../QG/QG_RE_Famiglie.pdf?#zoom=85&scrollbar=1&toolbar=0&navpanes=1&page=";
            $("#pdfcontent").attr("src", u + r);
            $("#RE_HELP").modal();
            break;
        case"4":
        case"5":
        case"9":
            t == "" ? r = "1" - i : t == "RE_Help_Menu" ? r = "8" - i : t == "RE_Help_Sign" ? r = "15" - i : t == "RE_Help_REC_Giornaliero" ? r = "23" - i : t == "RE_Help_REC_Appello" ? r = "18" - i : t == "RE_Help_REC_Settimanale" ? r = "31" - i : t == "RE_Help_RED_Planning" ? r = "33" - i : t == "RE_Help_RED_Completo" ? r = "38" - i : t == "RE_Help_RED_Competenze_Completo" ? r = "46" - i : t == "RE_Help_RED_Giornaliero" ? r = "40" - i : t == "RE_Help_RED_Colloqui" ? r = "47" - i : t == "RE_Help_RED_Diario" ? r = "48" - i : t == "RE_Help_RED_Apprendimento" ? r = "49" - i : t == "RE_Help_RED_Sostegno" ? r = "51" - i : t == "RE_Help_RED_Annotazioni" ? r = "50" - i : t == "RE_Help_RED_QuadroRiepilogativo" ? r = "54" - i : t == "RE_Help_RED_PRG_DID" ? r = "65" - i : t == "RE_Help_RED_Programmazione_Menu" ? r = "56" - i : t == "RE_Help_RED_Competenze" ? r = "57" - i : t == "RE_Help_RED_Programmazione_TAB" ? r = "68" - i : t == "RE_Help_RED_Libri_di_Testo" ? r = "68" - i : t == "RE_Help_SCR_VotiProposti" ? r = "70" - i : t == "RE_Help_SCR_Tabellone" ? r = "76" - i : t == "RE_Help_SCR_RC_Modalita" ? r = "84" - i : t == "RE_Help_SCR_RC" ? r = "85" - i : t == "RE_Help_SCR_RC_Tabellone" ? r = "87" - i : t == "RE_Help_SCR_Giudizi" ? r = "88" - i : t == "RE_Help_ALT_Pannelli" ? r = "90" - i : t == "RE_Help_ALT_Riepiloghi_Statistiche" ? r = "101" - i : t == "RE_Help_ALT_GPC" && (r = "108" - i);
            u = "../QG/QG_RE_Docenti.pdf?#zoom=85&scrollbar=1&toolbar=0&navpanes=1&page=";
            $("#pdfcontent").attr("src", u + r);
            $("#RE_HELP").modal();
            break;
        default:
            return
    }
}

function Today() {
    var i = new Date, n = i.getDate(), t = i.getMonth() + 1, r = i.getFullYear();
    return n < 10 && (n = "0" + n), t < 10 && (t = "0" + t), n + "/" + t + "/" + r
}

function NormalizzaData(n) {
    return n.substr(8, 2) + "/" + n.substr(5, 2) + "/" + n.slice(0, 4)
}

function Left(n, t) {
    return t <= 0 ? "" : t > String(n).length ? n : String(n).substring(0, t)
}

function Right(n, t) {
    if (t <= 0) return "";
    if (t > String(n).length) return n;
    var i = String(n).length;
    return String(n).substring(i, i - t)
}

function round(n, t) {
    var i = n * Math.pow(10, t);
    return i = Math.round(i), i / Math.pow(10, t)
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n)
}

function UCase(n) {
    var t = n.toString();
    return t.toUpperCase()
}

function LCase() {
    var n = StringToUpperCase.toString();
    return n.toLowerCase()
}

function REOnChange() {
    setSaveFlag(!0)
}

function RemoveCR(n) {
    return n.replace(/(\r\n|[\r\n])/g, " ")
}

function CheckDangerousChars(n) {
    return !/\W/i.test(n)
}

function checkEmail(n) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(String(n).toLowerCase())
}

function checkUniqueInList(n) {
    var t, i;
    t = document.getElementById("szItemList").value;
    i = t.indexOf(n.value);
    i != -1 && (alert("La data digitata Ã¨ giÃ  presente. Digitare una data corretta per il colloquio che si vuole inserire."), n.value = document.getElementById("initDate").value, setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1))
}

function ValidateDate(n) {
    var t = n, i;
    return /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/.test(t) ? (anno = parseInt(t.substr(6), 10), mese = parseInt(t.substr(3, 2), 10), giorno = parseInt(t.substr(0, 2), 10), i = new Date(anno, mese - 1, giorno), i.getFullYear() == anno && i.getMonth() + 1 == mese && i.getDate() == giorno ? !0 : !1) : !1
}

function setPosition(n, t, i) {
    var o, s, c, l, u = [], f, r;
    u = getPageSize();
    o = u[0];
    s = u[1];
    c = u[2];
    l = u[3];
    var a = parseInt(document.getElementById(n).style.width), e = parseInt(document.getElementById(n).style.height),
        h = document.layers ? "" : "px";
    e = isNaN(e) ? 300 : e;
    f = (o - a) / 2;
    r = (s - e) / 2;
    r += parseInt(t);
    r = Math.max(r, 5);
    f += parseInt(i);
    f = Math.max(f, 5);
    document.getElementById(n).style.left = f + h;
    r > 350 && (r = 350);
    document.getElementById(n).style.top = r + h
}

function setParentPosition(n, t, i) {
    var o, s, c, l, r = [], f, e;
    r = getPageSize();
    o = r[0];
    s = r[1];
    c = r[2];
    l = r[3];
    var a = parseInt(window.parent.document.getElementById(n).style.width),
        u = parseInt(window.parent.document.getElementById(n).style.height),
        h = window.parent.document.layers ? "" : "px";
    u = isNaN(u) ? 300 : u;
    f = (o - a) / 2;
    e = (s - u) / 2;
    e += parseInt(t);
    f += parseInt(i);
    window.parent.document.getElementById(n).style.left = f + h;
    window.parent.document.getElementById(n).style.top = e + h
}

function getPageSize() {
    var i, r, n, t;
    return window.innerHeight && window.scrollMaxY ? (i = window.innerWidth + window.scrollMaxX, r = window.innerHeight + window.scrollMaxY) : document.body.scrollHeight > document.body.offsetHeight ? (i = document.body.scrollWidth, r = document.body.scrollHeight) : (i = document.body.offsetWidth, r = document.body.offsetHeight), self.innerHeight ? (n = document.documentElement.clientWidth ? document.documentElement.clientWidth : self.innerWidth, t = self.innerHeight) : document.documentElement && document.documentElement.clientHeight ? (n = document.documentElement.clientWidth, t = document.documentElement.clientHeight) : document.body && (n = document.body.clientWidth, t = document.body.clientHeight), pageHeight = r < t ? t : r, pageWidth = i < n ? i : n, arrayPageSize = [pageWidth, pageHeight, n, t]
}

function ApplyWait() {
    $.blockUI({
        css: {border: "none", backgroundColor: "none"},
        message: '<img src="../Images/Image_Loading.gif" />',
        baseZ: 2e3
    })
}

function HideWait() {
    $.unblockUI()
}

function ApplyOpacity(n) {
    n === 1 ? ($("#overlay", document).show(), $("#overlay", document).css({opacity: .8})) : ($("#overlay", parent.document).hide(), $("#overlay", parent.document).css({opacity: 0}))
}

function DataSelectedinRegistroCompleto(n, t, i, r, u) {
    u = !0;
    i == 0 && n != 0 ? (document.getElementById("ContentPlaceHolderBody_idAluSelected").value = t, document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == n ? (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", u != !0 && (document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy"), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = "nothing", document.getElementById("ContentPlaceHolderBody_idAluSelected").value = "nothing") : (document.getElementById("Alu#" + n).style.backgroundColor = "#FFFF00", document.getElementById("Alu#" + n).style.color = "#000000", document.getElementById("AluV#" + n).style.backgroundColor = "#FFFF00", document.getElementById("AluV#" + n).style.color = "#000000", u != !0 && (document.getElementById("AluR#" + n).style.backgroundColor = "#FFFF00", document.getElementById("AluR#" + n).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == "nothing" || (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", u != !0 && (document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy")), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = n)) : i != 0 && n != 0 ? (document.getElementById("ContentPlaceHolderBody_idAluSelected").value = t, document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == n || (document.getElementById("Alu#" + n).style.backgroundColor = "#FFFF00", document.getElementById("Alu#" + n).style.color = "#000000", document.getElementById("AluV#" + n).style.backgroundColor = "#FFFF00", document.getElementById("AluV#" + n).style.color = "#000000", u != !0 && (document.getElementById("AluR#" + n).style.backgroundColor = "#FFFF00", document.getElementById("AluR#" + n).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == "nothing" || (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluV#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", u != !0 && (document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("AluR#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy")), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = n), document.getElementById("ContentPlaceHolderBody_DataSelected").value = r, document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == i || (Left(i, 4) == "Data" ? (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000", document.getElementById(i.replace("Data", "dtDown")).style.backgroundColor = "#FFFF00", document.getElementById(i.replace("Data", "dtDown")).style.color = "#000000") : (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000", document.getElementById(i.replace("dtDown", "Data")).style.backgroundColor = "#FFFF00", document.getElementById(i.replace("dtDown", "Data")).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == "nothing" || (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.color = "#FFFFFF")), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = i, document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = NormalizzaData(r))) : i != 0 && n == 0 && (document.getElementById("ContentPlaceHolderBody_DataSelected").value = r, document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == i ? (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.color = "#FFFFFF"), document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = Today(), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = "nothing", document.getElementById("ContentPlaceHolderBody_DataSelected").value = "nothing") : (Left(i, 4) == "Data" ? (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000", document.getElementById(i.replace("Data", "dtDown")).style.backgroundColor = "#FFFF00", document.getElementById(i.replace("Data", "dtDown")).style.color = "#000000") : (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000", document.getElementById(i.replace("dtDown", "Data")).style.backgroundColor = "#FFFF00", document.getElementById(i.replace("dtDown", "Data")).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == "nothing" || (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("Data", "dtDown")).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value.replace("dtDown", "Data")).style.color = "#FFFFFF")), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = i, document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = NormalizzaData(r)))
}

function DataSelectedinRegistroCompletoNonVedenti(n, t, i, r) {
    i == 0 && n != 0 ? (document.getElementById("ContentPlaceHolderBody_idAluSelected").value = t, document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == n ? (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy", document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = "nothing", document.getElementById("ContentPlaceHolderBody_idAluSelected").value = "nothing") : (document.getElementById("Alu#" + n).style.backgroundColor = "#FFFF00", document.getElementById("Alu#" + n).style.color = "#000000", document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == "nothing" || (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy"), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = n)) : i != 0 && n != 0 ? (document.getElementById("ContentPlaceHolderBody_idAluSelected").value = t, document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == n || (document.getElementById("Alu#" + n).style.backgroundColor = "#FFFF00", document.getElementById("Alu#" + n).style.color = "#000000", document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value == "nothing" || (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.backgroundColor = "transparent", document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value).style.color = "navy"), document.getElementById("ContentPlaceHolderBody_LastidAluSelected").value = n), document.getElementById("ContentPlaceHolderBody_DataSelected").value = r, document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == i || (Left(i, 4) == "Data" ? (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000") : (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == "nothing" || (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF")), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = i, document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = NormalizzaData(r))) : i != 0 && n == 0 && (document.getElementById("ContentPlaceHolderBody_DataSelected").value = r, document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == i ? (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF"), document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = Today(), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = "nothing", document.getElementById("ContentPlaceHolderBody_DataSelected").value = "nothing") : (Left(i, 4) == "Data" ? (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000") : (document.getElementById(i).style.backgroundColor = "#FFFF00", document.getElementById(i).style.color = "#000000"), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value == "nothing" || (Left(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value, 4) == "Data" ? (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF") : (document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.backgroundColor = "#999999", document.getElementById(document.getElementById("ContentPlaceHolderBody_LastDataSelected").value).style.color = "#FFFFFF")), document.getElementById("ContentPlaceHolderBody_LastDataSelected").value = i, document.getElementById("ContentPlaceHolderMenu_txtDataSelezionata").value = NormalizzaData(r)))
}

function myMaxlength(n, t, i) {
    var r = t - n.value.length;
    if (i = "cnt" + n.name, i != null) try {
        window.parent.document.getElementById(i).innerHTML = r
    } catch (u) {
    }
    if (r < 0 && (n.value = n.value.substring(0, t), i != null)) try {
        window.parent.document.getElementById(i).innerHTML = "0"
    } catch (u) {
    }
}

function ChangeStatus(n, t) {
    document.getElementById(t).value = document.getElementById(t).value == "N" || document.getElementById(t).value == "I" || document.getElementById(t).value == "" ? "I" : "U";
    REOnChange()
}

function ShowHideFirme(n, t) {
    var u, i, r;
    if (n && (u = document.getElementById("idxLayerFirme"), i = u.contentWindow || u.contentDocument, i.document)) for (i = i.document, r = 0; r <= 10; r++) try {
        i.getElementById("idMessage1#O" + Right("00" + r, 2)).style.display = "none";
        i.getElementById("idtxtMessage1#O" + Right("00" + r, 2)).value = "";
        i.getElementById("idMessage1#O" + Right("00" + r, 2)).style.display = "none";
        i.getElementById("idtxtMessage1#O" + Right("00" + r, 2)).value = ""
    } catch (f) {
    }
    try {
        window.parent.document.getElementById("idFrameFirme").style.display == "none" ? (setPosition("idFrameFirme", -150, 0), ApplyOpacity(1), window.parent.document.getElementById("idFrameFirme").style.display = "") : (ApplyOpacity(0), window.parent.document.getElementById("idFrameFirme").style.display = "none", t || parent.UpdateFromIFrameBSave(), parent.ShowRiepilogoFirme())
    } catch (f) {
        document.getElementById("idFrameFirme").style.display == "none" ? (setPosition("idFrameFirme", -150, 0), ApplyOpacity(1), document.getElementById("idFrameFirme").style.display = "") : (ApplyOpacity(0), window.parent.document.getElementById("idFrameFirme").style.display = "none", t || parent.UpdateFromIFrameBSave())
    }
}

function DataSelectedinVotiProposti(n, t) {
    document.getElementById("ContentPlaceHolderBody_idAluSelected").value = t;
    document.getElementById("ContentPlaceHolderBody_idAluSelectedSet").value = t;
    document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value === n ? (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value).style.backgroundColor = "transparent", document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value = "nothing", document.getElementById("ContentPlaceHolderBody_idAluSelectedSet").value = "nothing", document.getElementById("ContentPlaceHolderBody_idAluSelected").value = "nothing") : (document.getElementById("Alu#" + n).style.backgroundColor = "#ffffa0", document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value !== "nothing" && (document.getElementById("Alu#" + document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value).style.backgroundColor = "transparent"), document.getElementById("ContentPlaceHolderBody_LastidAluSelectedSet").value = n)
}

function ShowHideDiv_GiudizioVotiProposti(n, t, i, r) {
    for (var u = 0; u <= t; u++) document.getElementById("GiudizioID#R" + Right("00" + u, 2)).style.display = "none", document.getElementById("AnnotazioniID#R" + Right("00" + u, 2)).style.display = "none";
    if (document.getElementById("GiudizioAnnotazioniLayer").style.display == "" && i != 1) {
        ApplyOpacity(0);
        document.getElementById("GiudizioAnnotazioniLayer").style.display = "none";
        return
    }
    ApplyOpacity(0);
    document.getElementById("GiudizioAnnotazioniLayer").style.display = "none";
    i == 0 ? (setPosition("GiudizioAnnotazioniLayer", 0, 0), document.getElementById("GiudizioID#R" + Right("00" + n, 2)).style.display = "", ApplyOpacity(1), document.getElementById("GiudizioAnnotazioniLayer").style.display = "", document.getElementById("GiudizioHidFinale#R" + Right("00" + n, 2)).value = document.getElementById("GiudizioFinale#R" + Right("00" + n, 2)).value, REOnChange()) : i == 1 && (r == 0 ? (ApplyOpacity(0), document.getElementById("GiudizioAnnotazioniLayer").style.display = "none", document.getElementById("GiudizioHidFinale#R" + Right("00" + n, 2)).value = document.getElementById("GiudizioFinale#R" + Right("00" + n, 2)).value, REOnChange()) : (ApplyOpacity(0), document.getElementById("GiudizioFinale#R" + Right("00" + n, 2)).value = document.getElementById("GiudizioHidFinale#R" + Right("00" + n, 2)).value))
}

function ShowHideDiv_AnnotazioniVotiProposti(n, t, i, r) {
    for (var u = 0; u <= t; u++) document.getElementById("GiudizioID#R" + Right("00" + u, 2)).style.display = "none", document.getElementById("AnnotazioniID#R" + Right("00" + u, 2)).style.display = "none";
    if (document.getElementById("GiudizioAnnotazioniLayer").style.display == "" && i != 1) {
        ApplyOpacity(0);
        document.getElementById("GiudizioAnnotazioniLayer").style.display = "none";
        return
    }
    document.getElementById("GiudizioAnnotazioniLayer").style.display = "none";
    i == 0 ? (setPosition("GiudizioAnnotazioniLayer", 0, 0), document.getElementById("AnnotazioniID#R" + Right("00" + n, 2)).style.display = "", ApplyOpacity(1), document.getElementById("GiudizioAnnotazioniLayer").style.display = "", document.getElementById("AnnotazioniHidFinale#R" + Right("00" + n, 2)).value = document.getElementById("AnnotazioniFinale#R" + Right("00" + n, 2)).value, REOnChange()) : i == 1 && (r == 0 ? (ApplyOpacity(0), document.getElementById("GiudizioAnnotazioniLayer").style.display = "none", document.getElementById("AnnotazioniHidFinale#R" + Right("00" + n, 2)).value = document.getElementById("AnnotazioniFinale#R" + Right("00" + n, 2)).value, REOnChange()) : (ApplyOpacity(0), document.getElementById("AnnotazioniFinale#R" + Right("00" + n, 2)).value = document.getElementById("AnnotazioniHidFinale#R" + Right("00" + n, 2)).value))
}

function isDateCorrect(n, t) {
    var o = n.value, r = o.match(/^(\d{1,2})(\/)(\d{1,2})(\/)(\d{4})$/), i = !0, e, f, u;
    return r == null ? (alert("Inserire data nel formato gg/mm/aaaa."), n.value = Today(), setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1), i = !1) : (day = r[1], month = r[3], year = r[5], day < 1 || day > 31 ? (alert("Il Giorno deve essere compreso tra 1 e 31."), n.value = Today(), setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1), i = !1) : month < 1 || month > 12 ? (alert("Il Mese deve essere compreso tra 1 e 12."), n.value = Today(), setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1), i = !1) : (month == 4 || month == 6 || month == 9 || month == 11) && day == 31 ? (alert("Il Mese " + month + " non ha 31 Giorni!"), n.value = Today(), setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1), i = !1) : month == 2 && (e = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0), (day > 29 || day == 29 && !e) && (alert("Febbraio " + year + " non ha " + day + "Giorni!"), n.value = Today(), setTimeout("document.getElementsByName('" + n.name + "')[0].focus()", 1), i = !1))), i ? strLastDate = n.value : t && (strLastDate == "" ? (f = "01/09/", u = new Date, f += u.getMonth() + 1 < 9 ? u.getFullYear() - 1 : u.getFullYear(), n.value = f) : n.value = strLastDate), i
}

function CopyVotiProposti_InScrutini() {
    var r, u, t, i, n;
    for (r = document.getElementById("CounterMateriePresenti").value, u = document.getElementById("CounterAlunniPresenti").value, document.body.style.cursor = "wait", iAlu = 2; iAlu <= u; iAlu++) {
        for (iMat = 1; iMat <= r; iMat++) for (t = 1; t <= 5; t++) try {
            n = "";
            i = document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].value;
            ($.trim(i) == "" || i == "0") && (n = document.getElementsByName("txVotoProp#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "_p#" + iAlu)[0].value, n == "ES" || document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].id == "nomedia" || UCase(n) == "NC" ? isNumber(n) ? (n = n.replace(",", "."), document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].value = parseInt(parseFloat(n) + .5)) : document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].value = n : ($.trim(n) == "" ? document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].value = "" : (n = n.replace(",", "."), document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].value = parseInt(parseFloat(n) + .5)), document.getElementsByName("txVoto#fs_vt_" + Right("00" + iMat, 2) + "_" + t + "#" + iAlu)[0].style.color = n < 6 ? "red" : "#555555", REOnChange()))
        } catch (f) {
            REOnChange();
            document.body.style.cursor = "default"
        }
        try {
            (document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value == "" || document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value == "0") && (n = "", n = document.getElementsByName("txVotoProp#fs_cond_vt_p#" + iAlu)[0].value, $.trim(n) == "" ? document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value = "" : (n = n.replace(",", "."), document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value = parseInt(parseFloat(n) + .5)), (document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value == "" || document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value == "NaN") && (document.getElementsByName("txVoto#fs_cond_vt#" + iAlu)[0].value = "0"), REOnChange())
        } catch (f) {
        }
    }
    REOnChange();
    document.body.style.cursor = "default"
}

function CopyVotiProposti_InVotiProposti() {
    var r, i, u, f, t, n;
    if (r = document.getElementById("CounterAlunniPresenti").value, u = document.getElementById("EsitoFinale").value, bVotoUnicoForzato = document.getElementById("VotoUnicoForzato").value, document.body.style.cursor = "wait", f = confirm("Attenzione! Saranno sovrascritte tutte le Assenze Proposte e saranno sovrascritti solo i voti uguali a 0. Continuo ?"), f == !1) {
        document.body.style.cursor = "default";
        return
    }
    for (iAlu = 0; iAlu <= r; iAlu++) {
        try {
            document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C34").value = document.getElementById("txtHIDVP#A#" + Right("00" + iAlu, 2)).value;
            document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C34").value == "" && (document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C34").value = "0", REOnChange())
        } catch (e) {
        }
        if (u == 1 || bVotoUnicoForzato == 1) t = document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C33").value, $.trim(t) == "" && (t = "0"), parseFloat(t) === 0 && (n = document.getElementById("txtHIDVP#T#" + Right("00" + iAlu, 2)).value, document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C33").value = parseFloat(n.replace(",", ".")) === parseInt(n) ? parseInt(n) : n.substr(0, 4), REOnChange()); else for (i = 0; i <= 4; i++) try {
            t = document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C" + (29 + i)).value;
            $.trim(t) == "" && (t = "0");
            parseFloat(t) === 0 && (n = document.getElementById("txtHIDVP#" + i + "#" + Right("00" + iAlu, 2)).value, document.getElementById("vtProp#R" + Right("00" + iAlu, 2) + "#C" + (29 + i)).value = parseFloat(n.replace(",", ".")) === parseInt(n) ? parseInt(n) : n.substr(0, 4), REOnChange())
        } catch (e) {
            REOnChange();
            document.body.style.cursor = "default"
        }
    }
    REOnChange();
    document.body.style.cursor = "default"
}

function ApplicaModalitaRecuperoCarenzeATutti(n, t, i) {
    for (var u = document.getElementById("cmbTipoRec#" + n + "#" + Right("00" + t, 2)).value, r = 0; r <= i; r++) r != t && (document.getElementById("cmbTipoRec#" + n + "#" + Right("00" + r, 2)).value = u)
}

function ShowHideDiv_DettaglioVoti(n, t) {
    for (var i = 0; i <= t; i++) document.getElementById("DettaglioVotiTable#R" + Right("00" + i, 2)).style.display = "none";
    document.getElementById("DettaglioVotiTableLayer").style.display == "" ? (ApplyOpacity(0), document.getElementById("DettaglioVotiTableLayer").style.display = "none") : (setPosition("DettaglioVotiTableLayer", 0, 0), document.getElementById("DettaglioVotiTable#R" + Right("00" + n, 2)).style.display = "", ApplyOpacity(1), document.getElementById("DettaglioVotiTableLayer").style.display = "")
}

function DateDiff(n, t) {
    var r = n.substring(0, 2), u = n.substring(3, 5), f = n.substring(6, 10), i = new Date(f + "-" + u + "-" + r);
    i.setDate(i.getDate() - parseInt(t));
    var e = i.getDate(), o = i.getMonth() + 1, s = i.getFullYear();
    return Right("00" + e, 2) + "/" + Right("00" + o, 2) + "/" + s
}

function AluSelectedInFamily(n, t, i) {
    var r;
    if ($("#ContentPlaceHolderBody_divContent").html(""), document.getElementById("ContentPlaceHolderBody_txtAluSelected").value == i) document.getElementById("first#" + Right("000" + n, 2)).style.backgroundColor = "transparent", document.getElementById("second#" + Right("000" + n, 2)).style.backgroundColor = "transparent", document.getElementById("ContentPlaceHolderBody_txtAluSelected").value = "nothing", document.getElementById("ContentPlaceHolderBody_txtIDAluSelected").value = "nothing", DoPostBack("FAMILY", "NoSelect"); else {
        for (r = 0; r <= t; r++) document.getElementById("first#" + Right("000" + r, 2)).style.backgroundColor = "transparent", document.getElementById("second#" + Right("000" + r, 2)).style.backgroundColor = "transparent";
        document.getElementById("first#" + Right("000" + n, 2)).style.backgroundColor = "yellow";
        document.getElementById("second#" + Right("000" + n, 2)).style.backgroundColor = "yellow";
        document.getElementById("ContentPlaceHolderBody_txtAluSelected").value = i;
        document.getElementById("ContentPlaceHolderBody_txtIDAluSelected").value = n;
        $("#content-comunicazioni").html("<br/>Selezionare i dati da visualizzare cliccando su una delle icone sopra.");
        $("#ButtonCalendario").hide();
        $("#ContentPlaceHolderMenu_ButtonStampa").hide();
        $("#ContentPlaceHolderMenu_ButtonStampaRVA").hide();
        $("#ContentPlaceHolderMenu_ButtonSalva").hide();
        $("#btnSalvaPrenotazioni").hide();
        $("#ButtonPIN").hide()
    }
}

function AluAutoSelectedInFamily(n, t) {
    document.getElementById("first#" + Right("000" + n, 2)).style.backgroundColor = "yellow";
    document.getElementById("second#" + Right("000" + n, 2)).style.backgroundColor = "yellow";
    document.getElementById("ContentPlaceHolderBody_txtAluSelected").value = t;
    document.getElementById("ContentPlaceHolderBody_txtIDAluSelected").value = n
}

function clickCheckSign() {
    DoPostBack("SIGN", "")
}

function ShowHideDiv_FirmeApposte() {
    try {
        document.getElementById("idOreGiaFirmate").style.display == "none" ? (document.getElementById("idOreGiaFirmate").style.display = "", document.getElementById("idOreDaFirmare").style.display = "none", document.getElementById("Button3").value = "Aggiungi firme") : (document.getElementById("idOreGiaFirmate").style.display = "none", document.getElementById("idOreDaFirmare").style.display = "", document.getElementById("Button3").value = "Vedi chi ha giÃ  firmato")
    } catch (n) {
    }
}

function ShowMessageinFirme(n, t) {
    document.getElementById("idMessage1#O" + Right("00" + n, 2)).style.display = "";
    document.getElementById("idtxtMessage1#O" + Right("00" + n, 2)).value = t
}

function ShowMessageinOldFirme(n, t) {
    document.getElementById("idOldMessage1#O" + Right("00" + n, 2)).style.display = "";
    document.getElementById("idtxtOldMessage1#O" + Right("00" + n, 2)).value = t
}

function ShowUIDPWDAfterSign(n, t) {
    try {
        switch (t) {
            case 1:
                document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).disabled = !document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).disabled;
                document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).disabled = !document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).disabled;
                document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).disabled ? (document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#c6c6c6", document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).value = "XXXXX", document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#c6c6c6", document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).value = "XXXXX") : (document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#ffffff", document.getElementById("txtUIDIN#O" + Right("00" + n, 2)).value = "", document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#ffffff", document.getElementById("txtPWDIN#O" + Right("00" + n, 2)).value = "");
                break;
            case 2:
                document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).disabled = !document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).disabled;
                document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).disabled = !document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).disabled;
                document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).disabled ? (document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#c6c6c6", document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).value = "", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#c6c6c6", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).value = "") : (document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#ffffff", document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).value = "", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).style.backgroundColor = "#ffffff", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).value = "")
        }
    } catch (i) {
    }
}

function RetrieveDataFromDropDownList(n) {
    var i = document.getElementById("cmbNomeCognome#O" + Right("00" + n, 2)).value, t = i.split("##@##");
    t[0] != "" && (document.getElementById("txtNEWHIDUIDIN#O" + Right("00" + n, 2)).value = t[2], document.getElementById("txtNEWHIDPWDIN#O" + Right("00" + n, 2)).value = t[3], t[0] == document.getElementById("idMasterUser").value ? (document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).style.display = "none", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).style.display = "none") : (document.getElementById("txtNEWUIDIN#O" + Right("00" + n, 2)).style.display = "", document.getElementById("txtNEWPWDIN#O" + Right("00" + n, 2)).style.display = ""))
}

function CheckMatForDoc(n) {
    var f = document.getElementById("cmbNomeCognome#O" + Right("00" + n, 2)).value, t = f.split("##@##"),
        e = document.getElementById("cmbMateria#O" + Right("00" + n, 2)).value, i = e.split("##@##"), r, u;
    t[0] != "" && (r = document.getElementById("idDoc#" + t[0]).value, i[0] != "") && (u = r.search(i[0]), u == -1 && alert("L'accoppiata Docente-Materia Ã¨ incoerente. Questo Ã¨ soltanto un avviso. E' possibile continuare oppure correggere in caso di errore."))
}

function ResetStatusControlSign(n) {
    var t;
    try {
        document.getElementById("chkFirmaNEW#O" + Right("001", 2)).checked = ""
    } catch (i) {
    }
    for (t = 2; t <= 8; t++) {
        document.getElementById("cmbOra#O" + Right("00" + t, 2)).value = "";
        document.getElementById("cmbNomeCognome#O" + Right("00" + t, 2)).value = "";
        document.getElementById("cmbMateria#O" + Right("00" + t, 2)).value = "";
        document.getElementById("cmbTipo#O" + Right("00" + t, 2)).value = "";
        document.getElementById("chkFirmaNEW#O" + Right("00" + t, 2)).checked = "";
        try {
            document.getElementById("txtNEWUIDIN#O" + Right("00" + t, 2)).value = "";
            document.getElementById("txtNEWPWDIN#O" + Right("00" + t, 2)).value = "";
            document.getElementById("txtNEWUIDIN#O" + Right("00" + t, 2)).disabled = "true";
            document.getElementById("txtNEWPWDIN#O" + Right("00" + t, 2)).disabled = "true";
            document.getElementById("txtNEWUIDIN#O" + Right("00" + t, 2)).style.backgroundColor = "#c6c6c6";
            document.getElementById("txtNEWPWDIN#O" + Right("00" + t, 2)).style.backgroundColor = "#c6c6c6";
            document.getElementById("idMessage1#O" + Right("00" + t, 2)).value = "";
            document.getElementById("idOldMessage1" + Right("00" + t, 2)).value = ""
        } catch (i) {
        }
    }
    for (t = 0; t <= n; t++) try {
        document.getElementById("chkFirmaIN#O" + Right("00" + t, 2)).checked = "checked";
        document.getElementById("txtUIDIN#O" + Right("00" + t, 2)).value = "";
        document.getElementById("txtPWDIN#O" + Right("00" + t, 2)).value = "";
        document.getElementById("txtUIDIN#O" + Right("00" + t, 2)).disabled = "true";
        document.getElementById("txtPWDIN#O" + Right("00" + t, 2)).disabled = "true";
        document.getElementById("txtUIDIN#O" + Right("00" + t, 2)).style.backgroundColor = "#c6c6c6";
        document.getElementById("txtPWDIN#O" + Right("00" + t, 2)).style.backgroundColor = "#c6c6c6";
        document.getElementById("idMessage1#O" + Right("00" + t, 2)).value = "";
        document.getElementById("idOldMessage1" + Right("00" + t, 2)).value = ""
    } catch (i) {
    }
}

function SelDeSelAll(n, t, i) {
    for (var r = 0; r <= i; r++) document.getElementById(t + Right("00" + r, 2)).checked = document.getElementById(n).checked
}

function ShowHideDiv_ArgComp_Planner(n, t, i, r) {
    for (var u = 0; u <= t; u++) document.getElementById("idDivData#" + Right("00" + u, 2)).style.display = "none";
    document.getElementById("idArgCompLayer").style.display == "none" ? (setPosition("idArgCompLayer", 0, 0), ApplyOpacity(1), document.getElementById("idArgCompLayer").style.display = "", document.getElementById("idDivData#" + Right("00" + n, 2)).style.display = "", document.getElementById("myHiddenArgomenti#" + Right("00" + n, 2)).value = document.getElementById("myArgomenti#" + Right("00" + n, 2)).value, document.getElementById("myHiddenCompiti#" + Right("00" + n, 2)).value = document.getElementById("myCompiti#" + Right("00" + n, 2)).value, r && (document.getElementById("myHiddenNodeDisciplinari#" + Right("00" + n, 2)).value = document.getElementById("NodeDisciplinari#" + Right("00" + n, 2)).value, document.getElementById("myHiddenAnnotazioni#" + Right("00" + n, 2)).value = document.getElementById("Annotazioni#" + Right("00" + n, 2)).value, document.getElementById("myHiddenComunicazioniDS#" + Right("00" + n, 2)).value = document.getElementById("ComunicazioniDS#" + Right("00" + n, 2)).value)) : (ApplyOpacity(0), document.getElementById("idArgCompLayer").style.display = "none", i ? (document.getElementById("myArgomenti#" + Right("00" + n, 2)).value = document.getElementById("myHiddenArgomenti#" + Right("00" + n, 2)).value, document.getElementById("myCompiti#" + Right("00" + n, 2)).value = document.getElementById("myHiddenCompiti#" + Right("00" + n, 2)).value, r && (document.getElementById("NodeDisciplinari#" + Right("00" + n, 2)).value = document.getElementById("myHiddenNodeDisciplinari#" + Right("00" + n, 2)).value, document.getElementById("Annotazioni#" + Right("00" + n, 2)).value = document.getElementById("myHiddenAnnotazioni#" + Right("00" + n, 2)).value, document.getElementById("ComunicazioniDS#" + Right("00" + n, 2)).value = document.getElementById("myHiddenComunicazioniDS#" + Right("00" + n, 2)).value)) : (document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).innerHTML = document.getElementById("myArgomenti#" + Right("00" + n, 2)).value, document.getElementById("dataMyComp#R" + Right("00" + n, 2)).innerHTML = document.getElementById("myCompiti#" + Right("00" + n, 2)).value, REOnChange()))
}

function PlanningIndexChange() {
    var r, i, t, n;
    r = document.getElementById("ContentPlaceHolderMenu_ddlMy").value;
    i = document.getElementById("ContentPlaceHolderMenu_ddlFilter").value;
    t = document.getElementById("iMaxDayPlanning").value;
    switch (r) {
        case"0":
            for (n = 0; n <= t - 1; n++) try {
                document.getElementById("otherData#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyNoteDisc#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyNote#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyComDS#R" + Right("00" + n, 2)).style.display = "none"
            } catch (u) {
            }
            switch (i) {
                case"0":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"1":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"2":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"3":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyNoteDisc#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"4":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyNote#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"5":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyComDS#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
            }
            break;
        case"1":
            for (n = 0; n <= t - 1; n++) try {
                document.getElementById("otherData#R" + Right("00" + n, 2)).style.display = "";
                document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyNoteDisc#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyNote#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataMyComDS#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataOthArgs#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataOthComp#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataOthNoteDisc#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataOthNote#R" + Right("00" + n, 2)).style.display = "none";
                document.getElementById("dataOthComDS#R" + Right("00" + n, 2)).style.display = "none"
            } catch (u) {
            }
            switch (i) {
                case"0":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthArgs#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthComp#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"1":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyArgs#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthArgs#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"2":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyComp#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthComp#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"3":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyNoteDisc#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthNoteDisc#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"4":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyNote#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthNote#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
                    break;
                case"5":
                    for (n = 0; n <= t - 1; n++) try {
                        document.getElementById("dataMyComDS#R" + Right("00" + n, 2)).style.display = "";
                        document.getElementById("dataOthComDS#R" + Right("00" + n, 2)).style.display = ""
                    } catch (u) {
                    }
            }
    }
}

function ShowHideDiv_SchedaSingoloAlunno(n, t, i, r, u) {
    if (u == 0) {
        window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display == "" && (window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "none");
        var f = document.getElementById("idxSchedaSingoloAlunnoLayer");
        f && (f.src = "REScrutiniLayer.aspx?Type=4&idDivAlunno=" + n + "&MaxAlu=" + t + "&bCancel=" + i + "&bLocked=" + r);
        setPosition("idSchedaSingoloAlunnoLayer", 0, 0);
        ApplyOpacity(1);
        window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = ""
    } else u == 1 && i == 1 ? (ShowHideDiv_SchedaSingoloAlunnoOverRide(n, t, i, r, u), ApplyOpacity(0), window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "none") : (ShowHideDiv_SchedaSingoloAlunnoOverRide(n, t, i, r, u), ApplyOpacity(0), window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "none")
}

function ShowHideDiv_SchedaSingoloAlunnoOverRide(n, t, i, r, u) {
    var f;
    if (u == 1) {
        if (ApplyOpacity(0), !i) {
            for (f = 1; f < 18; f++) {
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n)[0].style.color = document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n)[0].style.color = document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n)[0].style.color = document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n)[0].style.color = document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n)[0].style.color = document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_cond_vt#" + n)[0].value = document.getElementById("txVoto#fs_cond_vt#" + n).value;
                    window.parent.document.getElementsByName("txVoto#fs_cond_vt#" + n)[0].style.color = document.getElementsByName("txVoto#fs_cond_vt#" + n)[0].value < 6 ? "red" : "#555555"
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txAssenza#fi_ass_" + Right("00" + f, 2) + "#" + n)[0].value = document.getElementById("txAssenza#fi_ass_" + Right("00" + f, 2) + "#" + n).value
                } catch (e) {
                }
                try {
                    window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_car#" + n)[0].value = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_car#" + n).value
                } catch (e) {
                }
            }
            REOnChange()
        }
        return
    }
    for (ApplyOpacity(1), f = 1; f < 18; f++) {
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n)[0].value;
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n).style.color = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_1#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n)[0].value;
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n).style.color = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_2#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n)[0].value;
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n).style.color = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_3#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n)[0].value;
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n).style.color = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_4#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n)[0].value;
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n).style.color = document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_5#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_cond_vt#" + n).value = window.parent.document.getElementsByName("txVoto#fs_cond_vt#" + n)[0].value;
            document.getElementById("txVoto#fs_cond_vt#" + n).style.color = document.getElementById("txVoto#fs_cond_vt#" + n).value < 6 ? "red" : "#555555"
        } catch (e) {
        }
        try {
            document.getElementById("txAssenza#fi_ass_" + Right("00" + f, 2) + "#" + n).value = window.parent.document.getElementsByName("txAssenza#fi_ass_" + Right("00" + f, 2) + "#" + n)[0].value
        } catch (e) {
        }
        try {
            document.getElementById("txVoto#fs_vt_" + Right("00" + f, 2) + "_car#" + n).value = window.parent.document.getElementsByName("txVoto#fs_vt_" + Right("00" + f, 2) + "_car#" + n)[0].value
        } catch (e) {
        }
    }
}

function WizardGiudizio(n) {
    var u, t, s, f, i, e, h, o, r;
    for (h = document.getElementById("bAddPunct").checked, o = document.getElementById("bAddCR").checked, document.getElementById("GiudizioComposto").value = "", t = "", u = 0; u <= n + 1; u++) for (f = "MAT#R" + Right("0000" + u, 4), s = document.getElementsByName(f).length, r = 0; r < s; r++) i = document.getElementsByName(f)[r].value, document.getElementsByName(f)[r].checked && i != "Nessuna selezione" && (h ? (e = "", CanAddsPunctuation(t) && Left(i, 1) == UCase(Left(i, 1)) && (e = "."), t != "" && (t += e, EndsWithSpecialChar(t) || (t += " ")), o && EndsWithSpecialChar2(t) && (t += "\n"), t += i) : (t !== "" && (t += " ", o && EndsWithSpecialChar2(t) && (t += "\n")), t += i));
    document.getElementById("GiudizioComposto").value = t
}

function EndsWithSpecialChar(n) {
    var t = new String(n);
    return t.charCodeAt(t.length - 1) <= 32 ? !0 : !1
}

function EndsWithSpecialChar2(n) {
    var t = new String(n.trim());
    return t.charCodeAt(t.length - 1) < 32 || t.charCodeAt(t.length - 1) === 46 ? !0 : !1
}

function EndsWithPunctuations(n) {
    var t = new String(n), i = t.charAt(t.length - 1);
    return ".;:,!?".indexOf(i) != -1 ? !0 : !1
}

function CanAddsPunctuation(n) {
    var t = new String(n);
    return EndsWithSpecialChar(t) ? EndsWithPunctuations(t.substr(0, t.length - 1)) ? !1 : !0 : EndsWithPunctuations(t) ? !1 : !0
}

function ShowWizardGiudizio(n, t, i, r, u) {
    var e, f;
    document.getElementById("idFrameWizardGiudizio").style.display == "none" && (e = i + "#R" + Right("00" + r, 2), f = document.getElementById("idxLayerWizardGiudizio"), f && (f.src = "REGiudiziWizard.aspx?Key_ID=" + u + "&Control_ID=" + i + "&Idx=" + r + "&Alu_ID=" + t + "&Ctl_Text_Len=" + n), setPosition("idFrameWizardGiudizio", 0, 0), document.getElementById("idFrameWizardGiudizio").style.display = "")
}

function RetrieveSourceValue() {
    var r = document.getElementById("sourceControl").value,
        t = window.parent.document.getElementById("idxLayerWizardGiudizio"), n, i;
    t && (n = t.contentWindow || t.contentDocument, n = n.document, i = window.parent.document.getElementById(r).value, n.getElementById("GiudizioComposto").value = i, i.length > 0 && alert("ATTENZIONE! - Esiste giÃ  un Giudizio compilato, eventuali modifiche lo sovrascriveranno."))
}

function HideWizardGiudizio(n, t) {
    var i, r;
    n || (i = document.getElementById("sourceControl").value, window.parent.document.getElementById(i).value = document.getElementById("GiudizioComposto").value, myMaxlength(window.parent.document.getElementById(i), t));
    window.parent.document.getElementById("idFrameWizardGiudizio").style.display = "none";
    r = document.getElementById("idxLayerWizardGiudizio");
    r && (r.src = "")
}

function ResetWizardGiudizio(n) {
    for (var t = 0; t <= n; t++) try {
        document.getElementById("MAT_CLEAR#R" + Right("0000" + t, 4)).checked = "checked"
    } catch (i) {
    }
    document.getElementById("GiudizioComposto").value = ""
}

function ResizeTextAreaGiudizioSA(n, t) {
    document.getElementById(n).style.height == "200px" ? (document.getElementById(n).style.height = "15px", document.getElementById(t).src = "../images/black/RE_View_Giudizio_SA.png") : (document.getElementById(n).style.height = "200px", document.getElementById(t).src = "../images/black/RE_View_Not_Giudizio_SA.png")
}

function ResizeTextAreaGiudizio(n, t) {
    document.getElementById(n).style.height == "120px" ? (document.getElementById(n).style.height = "40px", document.getElementById(t).src = "../images/black/RE_View_Giudizio_SA.png") : (document.getElementById(n).style.height = "120px", document.getElementById(t).src = "../images/black/RE_View_Not_Giudizio_SA.png")
}

function ShowHideDiv_DirectGetPIN(n) {
    if (document.getElementById("idDirectGetPIN").style.display == "none") ApplyOpacity(1), setPosition("idDirectGetPIN", 0, 0), document.getElementById("idDirectGetPIN").style.display = ""; else if (ApplyOpacity(0), document.getElementById("idDirectGetPIN").style.display = "none", !n) {
        var t;
        t = document.getElementById("txtPIN").value;
        document.getElementById("txtPIN").value = "";
        CheckDangerousChars(t) ? DoPostBack("CHECK_PIN", t) : alert("PIN non valido, inserire solo lettere o numeri, grazie")
    }
}

function RicalcoloMedia() {
    var s = document.getElementById("totMaterie").value, y = document.getElementById("OrdineScuola_State").value, f,
        p = document.getElementById("ElencoIDVoti").value, h = document.getElementById("GestioneCrediti").value, r, e,
        t, i, n, u, o;
    for (f = 0, e = 0, n = 0; n <= s - 1; n++) if (isNaN(document.getElementById("MAT_State#" + Right("00" + n, 2))) && (r = document.getElementById("MAT_State#" + Right("00" + n, 2)).value, r.charAt(7) !== "N" && r.charAt(6) === "1")) if (r.charAt(5) === "1") document.getElementById("Voto13#" + Right("00" + n, 2)) != undefined && document.getElementById("Voto13#" + Right("00" + n, 2)).value.trim().length != 0 && (i = document.getElementById("Voto13#" + Right("00" + n, 2)).value, iValue = ReturnValueVotiFromKey(i.toUpperCase()), iValue > 0 && (e += parseInt(ReturnValueVotiFromKey(i.toUpperCase())), f += 1)); else for (u = 0; u <= 4; u++) r.charAt(u) === "1" && (i = document.getElementById("Voto" + (9 + u) + "#" + Right("00" + n, 2)).value, iValue = parseInt(ReturnValueVotiFromKey(i.toUpperCase())), iValue > 0 && (e += parseInt(ReturnValueVotiFromKey(i.toUpperCase())), f += 1));
    if (t = e / f, o = t < 6 ? "red" : "#303030", h == 1 && (document.getElementById("CreditiMed").style.color = o, document.getElementById("CreditiMed").value = round(t, 2), RicalcolaBandaOscillazione(t)), document.getElementById("Media").style.color = o, document.getElementById("Media").value = round(t, 2), $("#media123").length > 0) {
        $("#media3").val(round(t, 2));
        var c = $.isNumeric($("#media1").val().replace(",", ".")) ? parseFloat($("#media1").val().replace(",", ".")) : 0,
            l = $.isNumeric($("#media2").val().replace(",", ".")) ? parseFloat($("#media2").val().replace(",", ".")) : 0,
            a = t, v = (c * pUno + l * pDue + a * pTre) / 100;
        $("#media123").val(round(v, 2))
    }
    return t
}

function ReturnValueVotiFromKey(n) {
    var t = document.getElementById("ElencoIDVoti").value, u = t.split("Â§"), i = u.indexOf(n), r;
    return i >= 0 ? (t = document.getElementById("ElencoValoriVoti").value, r = t.split("Â§"), r[i]) : 0
}

function ValutaIndexPerBanda(n) {
    return n == 6 ? 0 : n > 6 && n <= 7 ? 1 : n > 7 && n <= 8 ? 2 : n > 8 && n <= 9 ? 3 : n > 9 && n <= 10 ? 4 : 5
}

function RicalcolaBandaOscillazione(n) {
    var u = $("#AnnoDiCorso").val(), f = $("#AnnoScolastico").val(), t = [], i, r;
    t = f < "2018" ? [["3-4", "4-5", "5-6", "6-7", "7-8", "0"], ["3-4", "4-5", "5-6", "6-7", "7-8", "0"], ["4-5", "5-6", "6-7", "7-8", "8-9", "0"]] : [["7-8", "8-9", "9-10", "10-11", "11-12", "0"], ["8-9", "9-10", "10-11", "11-12", "12-13", "0"], ["9-10", "10-11", "11-12", "13-14", "14-15", "7-8"]];
    i = parseInt(u) - 3;
    r = parseInt(ValutaIndexPerBanda(n));
    document.getElementById("BandaOsc").value = t[i][r]
}

function RicalcolaCreditoTotale() {
    var u = 25, o = parseInt(document.getElementById("TipoSommaCrediti").value),
        i = parseInt(document.getElementById("CredAP").value), n = parseInt(document.getElementById("CredAS").value),
        f = parseInt(document.getElementById("CredINT").value), r;
    isNaN(i) && (i = 0);
    isNaN(n) && (n = 0);
    isNaN(f) && (f = 0);
    r = o == 1 ? i + n + f : i + n;
    var s = RicalcoloMedia(), h = $("#AnnoDiCorso").val(), c = $("#AnnoScolastico").val(), e = [];
    c < "2018" ? e = [["3-4", "4-5", "5-6", "6-7", "7-8", "0-0"], ["3-4", "4-5", "5-6", "6-7", "7-8", "0-0"], ["4-5", "5-6", "6-7", "7-8", "8-9", "0-0"]] : (u = 40, e = [["7-8", "8-9", "9-10", "10-11", "11-12", "0-0"], ["8-9", "9-10", "10-11", "11-12", "12-13", "0-0"], ["9-10", "10-11", "11-12", "13-14", "14-15", "7-8"]]);
    var l = parseInt(h) - 3, a = parseInt(ValutaIndexPerBanda(s)), t = e[l][a], v = t.substring(0, t.indexOf("-")),
        y = t.substring(t.indexOf("-") + 1, t.length);
    (n < parseInt(v) || n > parseInt(y)) && alert("ATTENZIONE! - Il credito scolastico indicato non Ã¨ all'interno della banda di oscillazione");
    r == 0 && alert("ATTENZIONE! - Il credito totale risulta pari a 0.");
    r > u && alert("ATTENZIONE! - Il credito totale risulta pari maggiore di " + u + ".");
    document.getElementById("CredTOT").value = r
}

function Show_Scrutini_SSA(n, t, i, r) {
    var u = parseInt(n), h = window.parent.document.getElementById("IDAlunniList").value, e, f, o, s;
    e = h.split("##~##");
    f = e.length - 1;
    i == 2 ? ($("#Button1").hide(), $("#Button2").hide(), $("#Button3").hide(), window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "none", window.parent.__doPostBack("REFRESH", "")) :
        i == 3 ? (window.parent.document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "none", window.parent.DoPostBack("REFRESH", "")) :
            i == 1 ? ($("#Button1").hide(), $("#Button2").hide(), $("#Button3").hide(), DoPostBack("SSA_WRITE", "")) :
                i == 4 ? (u = (u - 1 + f) % f, DoPostBack("SSA_WRITE_PRES_" + u, e[u])) :
                    i == 5 ? (u = (u + 1) % f, DoPostBack("SSA_WRITE_POSS_" + u, e[u])) :
                        i == 6 ? (u = (u - 1 + f) % f, DoPostBack("SSA_WRITE_PREC_" + u, e[u])) :
                            i == 7 ? (u = (u + 1) % f, DoPostBack("SSA_WRITE_POST_" + u, e[u])) : document.getElementById("idSchedaSingoloAlunnoLayer").style.display == "none" && (ApplyOpacity(1), o = document.getElementById("idxSchedaSingoloAlunnoLayer"), o && (s = "REScrutini_SSA.aspx?ID_Alu=" + t + "&Row_ID=" + u + "&iRecCar=" + r, o.src = "", o.src = s), setPosition("idSchedaSingoloAlunnoLayer", 0, 0), document.getElementById("idSchedaSingoloAlunnoLayer").style.display = "")
}

function CopiaVotiProposti_SuDefinitivi_SSA() {
    for (var f = document.getElementById("totMaterie").value, r, i, t, n, u = 0; u <= f - 1; u++) if (t = Right("00" + u, 2), isNaN(document.getElementById("MAT_State#" + t))) if (r = document.getElementById("MAT_State#" + t).value, r.charAt(6) == 1) if (n = $("[id='Assenza#" + t + "']").val(), n !== undefined && (n === "" || n === "0") && $("[id='Assenza#" + t + "']").val($("[id='AssPro#" + t + "']").val()), r.charAt(5) == 1) n = document.getElementById("VotoProp13#" + t).innerHTML, n = n.replace(",", "."), isNumber(n) ? (n = parseInt(parseFloat(n) + .5), document.getElementById("Voto13#" + t).value = n, document.getElementById("Voto13#" + t).style.color = n < 6 ? "red" : "#303030") : (document.getElementById("Voto13#" + t).value = "0", document.getElementById("Voto13#" + t).style.color = "red"); else for (i = 0; i <= 4; i++) r.charAt(i) == 1 && (n = document.getElementById("VotoProp" + (9 + i) + "#" + t).innerHTML, n = n.replace(",", "."), isNumber(n) ? (n = parseInt(parseFloat(n) + .5), document.getElementById("Voto" + (9 + i) + "#" + t).value = n, document.getElementById("Voto" + (9 + i) + "#" + t).style.color = n < 6 ? "red" : "#303030") : (document.getElementById("Voto" + (9 + i) + "#" + t).value = "0", document.getElementById("Voto" + (9 + i) + "#" + t).style.color = "red")); else try {
        n = document.getElementById("VotoProp13#" + t).innerHTML;
        document.getElementById("Voto13#" + t).value = n
    } catch (e) {
        n = 0
    }
    RicalcoloMedia()
}

function CommandFromScrutini(n) {
    n == 0 ? (ApplyOpacity(0), document.getElementById("idVPQuestion").style.display = "none") : n == 1 && DoPostBack("COPY_VP", "")
}

function CopyVotiProposti_InScrutini_New() {
    document.getElementById("VPRequested").value == "1" ? alert("ATTENZIONE! - Non Ã¨ possibile effettuare la copia dei voti proposti su quelli definitivi in quanto l'operazione Ã¨ giÃ  stata eseguita. Se si vuol procedere comunque Ã¨ necessario andare nel pannello di controllo ed eliminare il relativo evento per la classe selezionata.") : DoPostBack("COPY_VP", "")
}

function ShowPreview(n, t, i, r, u, f, e) {
    var o = document.getElementById("HiddenViewerFile").value, h, s;
    document.getElementById("TypeSelected").value = n;
    document.getElementById("FileName").value = i;
    document.getElementById("FullPath").value = u;
    document.getElementById("HiddenModelsFolder").value = f;
    document.getElementById("HiddenTypeOfDocument").value = e;
    switch (n) {
        case 0:
            document.getElementById("FileViewer").innerHTML = o;
            document.getElementById("ModificaModello").src = "../images/black/RE_modifica_disabled.png";
            document.getElementById("EliminaModello").src = "../images/black/RE_elimina_disabled.png";
            document.getElementById("DuplicaModello").src = "../images/black/RE_duplica.png";
            break;
        case 1:
            document.getElementById("FileViewer").innerHTML = o;
            document.getElementById("ModificaModello").src = "../images/black/RE_modifica.png";
            document.getElementById("EliminaModello").src = "../images/black/RE_elimina.png";
            document.getElementById("DuplicaModello").src = "../images/black/RE_duplica.png";
            break;
        case 2:
            document.getElementById("FileViewer").innerHTML = "<table style=width:100%;height:100%><tr><td style=vertical-align:middle;text-align:center;>" + o + "<\/td><\/tr><\/table>";
            document.getElementById("ModificaModello").src = "../images/black/RE_modifica_disabled.png";
            document.getElementById("EliminaModello").src = "../images/black/RE_elimina.png";
            document.getElementById("DuplicaModello").src = "../images/black/RE_duplica.png";
            break;
        case 3:
            document.getElementById("FileViewer").innerHTML = "<table style=width:100%;height:100%><tr><td style=vertical-align:middle;text-align:center;>" + o + "<\/td><\/tr><\/table>";
            document.getElementById("ModificaModello").src = "../images/black/RE_modifica_disabled.png";
            document.getElementById("EliminaModello").src = "../images/black/RE_elimina_disabled.png";
            document.getElementById("DuplicaModello").src = "../images/black/RE_duplica.png"
    }
    for (document.getElementById(t).innerHTML = "Documento selezionato: " + i, h = document.getElementById("MaxElement").value, s = 0; s <= h - 1; s++) document.getElementById("iElement#" + Right("00" + s, 2)).style.backgroundColor = "transparent";
    document.getElementById("iElement#" + Right("00" + r, 2)).style.backgroundColor = "yellow"
}

function SelectTabber(n, t) {
    document.getElementById(t).tabber.tabShow(n)
}

function HideTabber(n, t) {
    document.getElementById(t).tabber.tabHide(n)
}

function ShowQuestion(n) {
    setPosition("idQuestion", 0, 0);
    switch (n) {
        case 0:
            if (Right(document.getElementById("ModificaModello").src, 13) == "_disabled.png") return;
            break;
        case 1:
            if (Right(document.getElementById("DuplicaModello").src, 13) == "_disabled.png") return;
            document.getElementById("LabelQuestion").innerHTML = "Duplicare il file " + document.getElementById("FileName").value + " ?";
            document.getElementById("Button50").value = "Ok";
            document.getElementById("CopiaFileSection").style.display = "";
            break;
        case 2:
            if (Right(document.getElementById("EliminaModello").src, 13) == "_disabled.png") return;
            document.getElementById("LabelQuestion").innerHTML = "Eliminare il file " + document.getElementById("FileName").value + " ?";
            document.getElementById("Button50").value = "Elimina";
            document.getElementById("CopiaFileSection").style.display = "none";
            break;
        case 3:
            document.getElementById("idQuestion").style.display = "none";
            document.getElementById("CopiaFileSection").style.display = "none";
            return
    }
    document.getElementById("idQuestion").style.display = ""
}

function ShowEditor(n, t, i, r) {
    if (document.getElementById("idEditorTemplate").style.display == "none") {
        if (typeof t == "undefined" && (t = parseInt(document.getElementById("TypeSelected").value), (t == 0 || t == 1) && Right(document.getElementById("ModificaModello").src, 13) == "_disabled.png")) return;
        ApplyOpacity(1);
        setPosition("idEditorTemplate", -50, -40);
        document.getElementById("idEditorTemplate").style.display = "";
        var f = document.getElementById("idxFrameEditor"), u = "controls/EditorHTML.aspx";
        if (f) {
            switch (t) {
                case 0:
                    u = u + "?iType=0&FP=" + document.getElementById("FullPath").value;
                    break;
                case 1:
                    u = u + "?iType=1&FP=" + document.getElementById("FullPath").value;
                    break;
                case 2:
                    u = u + "?iType=2&iComp=" + i;
                    break;
                case 3:
                    u = u + "?iType=3&iComp=" + i;
                    break;
                case 4:
                    u = u + "?iType=4&iDettComp=" + r + "&iComp=" + i;
                    break;
                case 5:
                    u = u + "?iType=5&iDettComp=" + r + "&iComp=" + i;
                    break;
                case 6:
                    u = u + "?iType=6&iComp=" + i;
                    break;
                case 7:
                    u = u + "?iType=7&iComp=" + i;
                    break;
                case 8:
                    u = u + "?iType=8&iDettComp=" + r + "&iComp=" + i;
                    break;
                case 9:
                    u = u + "?iType=9&iComp=" + i + "&iDettComp=" + r;
                    break;
                case 10:
                    u = u + "?iType=10&iComp=" + i
            }
            f.src = u
        }
    }
}

function ShowEditorForReport(n, t) {
    if (document.getElementById("idEditorTemplate").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idEditorTemplate", -500, -40);
        document.getElementById("idEditorTemplate").style.display = "";
        var r = document.getElementById("idxFrameEditor"), i = "controls/EditorHTML.aspx";
        r && (i = i + "?iType=2&FP=" + t, r.src = i)
    }
}

function CloseEditor(n, t) {
    if (n == "1") {
        window.parent.document.getElementById("idEditorTemplate").style.display = "none";
        window.parent.__doPostBack("REFRESH", "");
        return
    }
    switch (parseInt(t)) {
        case 0:
            window.parent.document.getElementById("FileViewer").innerHTML = document.all.item("oEdit").value;
            break;
        case 2:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 3:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 4:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 5:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 6:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 7:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 8:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 9:
            window.parent.__doPostBack("REFRESH", "");
            break;
        case 10:
            window.parent.document.getElementById("txtAnnotazioni#ADDNEWOBJ").value = document.all.item("oEdit").value
    }
    window.parent.document.getElementById("idEditorTemplate").style.display = "none";
    ApplyOpacity(0)
}

function CloseEditorForReport(n, t) {
    if (n == "1") {
        window.parent.document.getElementById("idEditorTemplate").style.display = "none";
        window.parent.__doPostBack("REFRESH_STAMPA", "");
        return
    }
    window.parent.__doPostBack("STAMPA_VERBALE_OVERO", t);
    ApplyOpacity(0)
}

function ExecuteAction() {
    if (document.getElementById("Button50").value == "Elimina") DoPostBack("ELIMINA_FILE", document.getElementById("FullPath").value); else {
        if (document.getElementById("NomeTemplate").value == "") {
            alert("E' necessario digitare il nome del nuovo template.");
            return
        }
        DoPostBack("DUPLICA_FILE##~##" + document.getElementById("NomeTemplate").value, document.getElementById("FullPath").value)
    }
}

function SalvaDatiScrutiniGestione() {
    DoPostBack("SALVA_DATI_SCRUTINI_GESTIONE", "")
}

function StampaVerbale() {
    var n;
    n = document.getElementById("VerbaleSelection").value;
    DoPostBack("STAMPA_VERBALE", n)
}

function ShowScrutiniGestione() {
    if (window.parent.document.getElementById("idxScrutiniLayerGestione").style.display == "none") {
        var n = document.getElementById("idxScrutiniGestione");
        n && (n.src = "REScrutini_Gestione.aspx");
        setPosition("idxScrutiniLayerGestione", 0, 0);
        ApplyOpacity(1);
        window.parent.document.getElementById("idxScrutiniLayerGestione").style.display = ""
    } else try {
        window.parent.document.getElementById("idxScrutiniLayerGestione").style.display = "none";
        window.parent.__doPostBack("REFRESH", "");
        ApplyOpacity(0)
    } catch (t) {
        document.getElementById("idxScrutiniLayerGestione").style.display = "none";
        __doPostBack("REFRESH", "");
        ApplyOpacity(0)
    }
}

function ShowPRGGestione(n, t, i, r) {
    var u, f;
    if (i == "1" && t == 0) {
        document.getElementById("idxCompetenzeLayerGestione").style.display == "none" && (ApplyOpacity(1), setPosition("idxCompetenzeLayerGestione", 0, 0), document.getElementById("idxCompetenzeLayerGestione").style.display = "", u = document.getElementById("idxCompetenzeGestione"), f = "RERED_Programmazione_TAB_M.aspx?IDCOMP=" + n + "&RO=0", u && (u.src = f));
        return
    }
    if (window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display == "none") ApplyOpacity(1), setPosition("idxCompetenzeLayerGestione", 0, 0), window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "", u = document.getElementById("idxCompetenzeGestione"), r == undefined && (r = 0), f = "RERED_Programmazione_TAB_M.aspx?IDCOMP=" + n + "&RO=" + r, u && (u.src = f); else try {
        if (t) {
            ResetParentLayer("idxCompetenzeGestione");
            window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtCOMPETENZA_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione. Riprovare.");
            return
        }
        if (document.getElementById("txtDataInizio").value.trim() == "" || !ValidateDate(document.getElementById("txtDataInizio").value)) {
            alert("Data inizio non valida");
            return
        }
        if (document.getElementById("txtDataFine").value.trim() == "" || !ValidateDate(document.getElementById("txtDataFine").value)) {
            alert("Data fine non valida");
            return
        }
        __doPostBack("SAVE_PRG", "SAVE_FILE_PARENT");
        window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "none"
    } catch (e) {
        if (t) {
            ResetLayer("idxCompetenzeGestione");
            document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtCOMPETENZA_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione. Riprovare.");
            return
        }
        if (document.getElementById("txtDataInizio").value.trim() == "" || !ValidateDate(document.getElementById("txtDataInizio").value)) {
            alert("Data inizio non valida");
            return
        }
        if (document.getElementById("txtDataFine").value.trim() == "" || !ValidateDate(document.getElementById("txtDataFine").value)) {
            alert("Data fine non valida");
            return
        }
        __doPostBack("SAVE_PRG", "SAVE_FILE");
        document.getElementById("idxCompetenzeLayerGestione").style.display = "none"
    }
}

function RefreshPRGGestione() {
    window.parent.__doPostBack("REFRESH", "")
}

function ShowDettagliPRG(n, t) {
    if (document.getElementById("idxObiettiviLayerLista").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxObiettiviLayerLista", 0, 0);
        document.getElementById("idxObiettiviLayerLista").style.display = "";
        var i = document.getElementById("idxObiettiviLista"),
            r = "RERED_Programmazione_TAB_D.aspx?IDCOMP=" + n + "&IDDETTCOMP=" + t;
        i && (i.src = r)
    }
}

function CloseDettagliPRG(n, t) {
    try {
        if (t) window.parent.__doPostBack("REFRESH", "ADD_PRG"); else {
            if (document.getElementById("txtDataInizio").value.trim() == "" || !ValidateDate(document.getElementById("txtDataInizio").value)) {
                alert("Data inizio non valida");
                return
            }
            if (document.getElementById("txtDataFine").value.trim() == "" || !ValidateDate(document.getElementById("txtDataFine").value)) {
                alert("Data fine non valida");
                return
            }
            __doPostBack("ADD_PRG", n)
        }
        window.parent.document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    } catch (i) {
        if (t) window.parent.__doPostBack("REFRESH", ""); else {
            if (document.getElementById("txtDataInizio").value.trim() == "" || !ValidateDate(document.getElementById("txtDataInizio").value)) {
                alert("Data inizio non valida");
                return
            }
            if (document.getElementById("txtDataFine").value.trim() == "" || !ValidateDate(document.getElementById("txtDataFine").value)) {
                alert("Data fine non valida");
                return
            }
            __doPostBack("ADD_PRG", n)
        }
        document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    }
}

function ShowListaCompetenze(n, t) {
    if (document.getElementById("idxObiettiviLayerLista").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxObiettiviLayerLista", 0, 0);
        document.getElementById("idxObiettiviLayerLista").style.display = "";
        var i = document.getElementById("idxObiettiviLista"),
            r = "REListaTabellaCompetenze.aspx?IDCOMP=" + n + "&IDDETTCOMP=" + t;
        i && (i.src = r)
    }
}

function CloseListaCompetenze(n, t) {
    try {
        t ? window.parent.__doPostBack("REFRESH", "ADD_COMP") : (__doPostBack("ADD_COMP", n), window.parent.__doPostBack("SAVE_UDA_DETAILS_BEFORE_COMP_INSERT", n));
        ResetParentLayer("idxObiettiviLista");
        window.parent.document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    } catch (i) {
        t ? window.parent.__doPostBack("REFRESH", "ADD_COMP") : (__doPostBack("ADD_COMP", n), window.parent.__doPostBack("SAVE_UDA_DETAILS_BEFORE_COMP_INSERT", n));
        ResetLayer("idxObiettiviLista");
        document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    }
}

function ShowCompetenzeGestione(n, t) {
    if (window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxCompetenzeLayerGestione", 0, 0);
        window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "";
        var i = document.getElementById("idxCompetenzeGestione"), r = "REGestioneCompetenze.aspx?IDCOMP=" + n;
        i && (i.src = r)
    } else try {
        if (t) {
            ResetParentLayer("idxCompetenzeGestione");
            window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtCOMPETENZA_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione per l'obiettivo che si sta salvando. Riprovare.");
            return
        }
        __doPostBack("SAVE_COMPETENZE", "");
        ResetParentLayer("idxCompetenzeGestione");
        window.parent.document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
        ApplyOpacity(0)
    } catch (u) {
        if (t) {
            ResetLayer("idxCompetenzeGestione");
            document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtCOMPETENZA_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione per l'obiettivo che si sta salvando. Riprovare.");
            return
        }
        __doPostBack("SAVE_COMPETENZE", "");
        ResetLayer("idxCompetenzeGestione");
        document.getElementById("idxCompetenzeLayerGestione").style.display = "none";
        ApplyOpacity(0)
    }
}

function RefreshCompetenzeGestione() {
    window.parent.__doPostBack("REFRESH", "")
}

function ShowCompetenzeDettagliGestione(n, t) {
    if (document.getElementById("idxCompetenzeDettagliLayerGestione").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxCompetenzeDettagliLayerGestione", 0, 0);
        document.getElementById("idxCompetenzeDettagliLayerGestione").style.display = "";
        var i = document.getElementById("idxCompetenzeDettagliGestione"),
            r = "REGestioneDettagliCompetenze.aspx?IDCOMP=" + n + "&IDDETTCOMP=" + t;
        i && (i.src = r)
    }
}

function CloseCompetenzeDettagliGestione(n, t, i) {
    try {
        if (i) {
            ResetParentLayer("idxCompetenzeDettagliGestione");
            window.parent.document.getElementById("idxCompetenzeDettagliLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtDETTAGLI_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione per il dettaglio dell'obiettivo che si sta salvando. Riprovare.");
            return
        }
        __doPostBack("SAVE_DETAILS_COMPETENZE", t);
        ResetParentLayer("idxCompetenzeDettagliGestione");
        window.parent.document.getElementById("idxCompetenzeDettagliLayerGestione").style.display = "none";
        ApplyOpacity(0)
    } catch (r) {
        if (i) {
            ResetLayer("idxCompetenzeDettagliGestione");
            document.getElementById("idxCompetenzeDettagliLayerGestione").style.display = "none";
            ApplyOpacity(0);
            return
        }
        if (document.getElementById("txtDETTAGLI_DESCRIZIONE").value.trim() == "") {
            alert("Non Ã¨ stato inserita nessuna descrizione per il dettaglio dell'obiettivo che si sta salvando. Riprovare.");
            return
        }
        __doPostBack("SAVE_DETAILS_COMPETENZE", t);
        ResetLayer("idxCompetenzeDettagliGestione");
        document.getElementById("idxCompetenzeDettagliLayerGestione").style.display = "none";
        ApplyOpacity(0)
    }
}

function ShowListaObiettivi(n, t) {
    if (document.getElementById("idxObiettiviLayerLista").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxObiettiviLayerLista", 0, 0);
        document.getElementById("idxObiettiviLayerLista").style.display = "";
        var i = document.getElementById("idxObiettiviLista"),
            r = "REListaTabellaObiettivi.aspx?IDCOMP=" + n + "&IDDETTCOMP=" + t;
        i && (i.src = r)
    }
}

function CloseListaObiettivi(n, t) {
    try {
        t ? window.parent.__doPostBack("REFRESH", "ADD_OBJ") : (__doPostBack("ADD_OBJ", n), window.parent.__doPostBack("SAVE_DETAILS_COMPETENZE_BEFORE_OBJ_INSERT", n));
        ResetParentLayer("idxObiettiviLista");
        window.parent.document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    } catch (i) {
        t ? window.parent.__doPostBack("REFRESH", "ADD_OBJ") : (__doPostBack("ADD_OBJ", n), window.parent.__doPostBack("SAVE_DETAILS_COMPETENZE_BEFORE_OBJ_INSERT", n));
        ResetLayer("idxObiettiviLista");
        document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    }
}

function ShowListaCompetenzeBase(n) {
    if (document.getElementById("idxObiettiviLayerLista").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxObiettiviLayerLista", 0, 0);
        document.getElementById("idxObiettiviLayerLista").style.display = "";
        var t = document.getElementById("idxObiettiviLista"), i = "RERED_Competenze_Lista.aspx?IDX=" + n;
        t && (t.src = i)
    }
}

function CloseListaCompetenzeBase(n, t, i, r, u) {
    var e, f;
    try {
        if (!t) {
            if (r == 1) {
                for (e = "", f = 0; f <= u; f++) document.all.item("MultiSelect#" + Right("0000" + f, 4)).checked && (e = e == "" ? document.all.item("textIndicatore#" + Right("0000" + f, 4)).value : e + "|" + document.all.item("textIndicatore#" + Right("0000" + f, 4)).value);
                window.parent.__doPostBack("SALVA_MULTISELECT", e);
                return
            }
            n != "999999" ? window.parent.document.getElementById("txtTestoObj#R" + Right("0000" + n, 4)).value = document.all.item("textIndicatore#" + Right("0000" + i, 4)).value : window.parent.document.getElementById("txtTestoObj#ADDNEWOBJ").value = document.all.item("textIndicatore#" + Right("0000" + i, 4)).value
        }
        ResetParentLayer("idxObiettiviLista");
        window.parent.document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    } catch (o) {
        t || (n != "999999" ? window.parent.document.getElementById("txtTestoObj#R" + Right("0000" + n, 4)).value = document.all.item("textIndicatore#" + Right("0000" + i, 4)).value : window.parent.document.getElementById("txtTestoObj#ADDNEWOBJ").value = document.all.item("textIndicatore#" + Right("0000" + i, 4)).value);
        ResetLayer("idxObiettiviLista");
        document.getElementById("idxObiettiviLayerLista").style.display = "none";
        ApplyOpacity(0)
    }
}

function ShowProgrammazioneRDOC(n) {
    if (document.getElementById("idxProgrammazioneRDOCLayer").style.display == "none") {
        ApplyOpacity(1);
        setPosition("idxProgrammazioneRDOCLayer", 0, 0);
        document.getElementById("idxProgrammazioneRDOCLayer").style.display = "";
        var t = document.getElementById("idxProgrammazioneRDOC"),
            i = "RERED_Programmazione_Lista.aspx?RDOC=true&IDDAY=" + n;
        t && (t.src = i)
    }
}

function CloseProgrammazioneRDOC(n, t, i, r) {
    var u = document.getElementById("IDDAY").value;
    try {
        t == "2" ? (window.parent.document.getElementById("progrDidMasterCodice#" + Right("00" + u, 2)).value = "", window.parent.document.getElementById("progrDidMaster#" + Right("00" + u, 2)).value = "", window.parent.document.getElementById("progrDidInfo#" + Right("00" + u, 2)).innerHTML = "<br><br>", window.parent.document.getElementById("progrDidDetailCodice#" + Right("00" + u, 2)).value = "", window.parent.document.getElementById("progrDidDetail#" + Right("00" + u, 2)).value = "") : t || (n == 1 ? (r == 1 ? (window.parent.document.getElementById("progrDidMasterCodice#" + Right("00" + u, 2)).value = document.getElementById("MasterOnlyCodice#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidMaster#" + Right("00" + u, 2)).value = document.getElementById("MasterOnly#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidInfo#" + Right("00" + u, 2)).innerHTML = "<b>" + fszCutString(document.getElementById("MasterOnly#R" + Right("000" + i, 3)).value, 50, 1) + "<\/b>", window.parent.document.getElementById("progrDidDetailCodice#" + Right("00" + u, 2)).value = "", window.parent.document.getElementById("progrDidDetail#" + Right("00" + u, 2)).value = "") : (window.parent.document.getElementById("progrDidMasterCodice#" + Right("00" + u, 2)).value = document.getElementById("MasterCodice#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidMaster#" + Right("00" + u, 2)).value = document.getElementById("Master#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidDetailCodice#" + Right("00" + u, 2)).value = document.getElementById("DetailCodice#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidDetail#" + Right("00" + u, 2)).value = document.getElementById("Detail#R" + Right("000" + i, 3)).value, window.parent.document.getElementById("progrDidInfo#" + Right("00" + u, 2)).innerHTML = "<b>" + fszCutString(document.getElementById("Master#R" + Right("000" + i, 3)).value, 50, 1) + "<\/b><br/>" + fszCutString(document.getElementById("Detail#R" + Right("000" + i, 3)).value, 50, 1)), REOnChange(), window.parent.document.getElementById("ArgClasse#" + Right("00" + u, 2)).value == "" && (r == 1 ? (window.parent.document.getElementById("ArgClasse#" + Right("00" + u, 2)).value = document.getElementById("MasterOnly#R" + Right("000" + i, 3)).value, REOnChange()) : (window.parent.document.getElementById("ArgClasse#" + Right("00" + u, 2)).value = document.getElementById("Detail#R" + Right("000" + i, 3)).value != "" ? document.getElementById("Detail#R" + Right("000" + i, 3)).value : document.getElementById("Master#R" + Right("000" + i, 3)).value, REOnChange()))) : __doPostBack("SAVE_PRG", ""));
        ResetParentLayer("idxProgrammazioneRDOC");
        window.parent.document.getElementById("idxProgrammazioneRDOCLayer").style.display = "none";
        ApplyOpacity(0)
    } catch (f) {
        t || n == 1 || __doPostBack("SAVE_PRG", "");
        ResetLayer("idxProgrammazioneRDOC");
        document.getElementById("idxProgrammazioneRDOCLayer").style.display = "none";
        ApplyOpacity(0)
    }
}

function CopiaCompetenzePRG(n, t, i, r) {
    n == 0 ? (document.getElementById(r).style.display = "none", ApplyOpacity(0)) : n == 1 ? (__doPostBack(i, t), document.getElementById(r).style.display = "none") : document.getElementById(r).style.display == "none" && (ApplyOpacity(1), setPosition(r, -100, 0), document.getElementById(r).style.display = "")
}

function fszCutString(n, t, i) {
    var r = n;
    return n.length > t && (r = Left(n, t), r.trim(), i == 1 && (r = r + "&nbsp;[...]")), r
}

function OnFocusTextArea(n, t) {
    n.value.indexOf(t) == 0 && (n.value = "");
    REOnChange()
}

function OnBlurTextArea(n, t) {
    n.value.trim() == "" && (n.value = t);
    REOnChange()
}

function SelectDivCompetenze(n) {
    for (var t = 1; t <= n; t++) document.getElementById("DivFilter" + Right("0000" + t, 4)).style.display = "none";
    document.getElementById(document.getElementById("Filter").value).style.display = ""
}

function DeselezionaRiga(n, t) {
    for (var r, u = document.getElementById("iMaxDay").value, i = 1; i <= t; i++) for (document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFFFF", r = 1; r <= u; r++) document.getElementById("DettagliAlunnoR#" + Right("00" + i, 2) + "#D" + Right("00" + r, 2)).style.display = "none";
    document.getElementById("DettagliVuoto").style.display = "";
    document.getElementById("AluSelectedInRDOC").value = ""
}

function EvidenziaRiga(n, t, i, r, u, f, e, o) {
    var y = document.getElementById("arDateRDOC").value, l = y.split("|"), c = document.getElementById("iMaxDay").value,
        a, v, h, s;
    if (v = u == undefined ? 1 : u == 0 ? 1 : 0, (r == undefined || r == 999) && (r = document.getElementById("iDaySelectedInRDOC").value), a = !1, document.getElementById("iDaySelectedInRDOC").value != r && (document.getElementById("iDaySelectedInRDOC").value = r, a = !0), u != 1) {
        for (s = 1; s <= c; s++) document.getElementById("GIORNO#" + s).style.color = "white";
        document.getElementById("GIORNO#" + r).style.color = "yellow";
        document.getElementById("DataSelezionataWeek").innerHTML = l[r - 1];
        document.getElementById("ContentPlaceHolderBody_txtDataSelezionataCAL").value = l[r - 1]
    }
    if (u != 1 && o != 1) {
        for (s = 1; s <= c; s++) document.getElementById("DettagliPersonali#" + Right("00" + s, 2)).style.display = "none", document.getElementById("DettagliAltriDocenti#" + Right("00" + s, 2)).style.display = "none";
        document.getElementById("DettagliPersonali#" + Right("00" + r, 2)).style.display = "";
        document.getElementById("DettagliAltriDocenti#" + Right("00" + r, 2)).style.display = ""
    }
    for (h = v; h <= t; h++) if (document.getElementById("RTAB#" + Right("00" + h, 2)).style.backgroundColor = "#FFFFFF", u != 1 && o != 1) for (s = 1; s <= c; s++) document.getElementById("DettagliAlunnoR#" + Right("00" + h, 2) + "#D" + Right("00" + s, 2)).style.display = "none";
    e == 1 && document.getElementById("AluSelectedInRDOC").value != "" && (i = document.getElementById("AluSelectedInRDOC").value, document.getElementById("AluSelectedInRDOC").value = "");
    document.getElementById("AluSelectedInRDOC").value == "" && i != 0 ? (document.getElementById("AluSelectedInRDOC").value = Right("00" + i, 2), document.getElementById("ContentPlaceHolderBody_idAluSelectedSet").value = f, document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFCBF", u == 1 ? document.getElementById("NoSelection").style.backgroundColor = "#FFFFFF" : o == 1 || (document.getElementById("DettagliAlunnoR#" + Right("00" + i, 2) + "#D" + Right("00" + r, 2)).style.display = "", document.getElementById("DettagliVuoto").style.display = "none", SelectTabber(1, "tabber"))) : document.getElementById("AluSelectedInRDOC").value == Right("00" + i, 2) ? (document.getElementById("AluSelectedInRDOC").value = "", document.getElementById("ContentPlaceHolderBody_idAluSelectedSet").value = "", document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFFFF", u == 1 || o == 1 || (document.getElementById("DettagliAlunnoR#" + Right("00" + i, 2) + "#D" + Right("00" + r, 2)).style.display = "none", document.getElementById("DettagliVuoto").style.display = "", document.getElementById("AluSelectedInRDOC").value = "", SelectTabber(0, "tabber"))) : (i != 0 || i == 0 && u == 1) && (document.getElementById("AluSelectedInRDOC").value = Right("00" + i, 2), document.getElementById("ContentPlaceHolderBody_idAluSelectedSet").value = f, document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFCBF", u == 1 ? document.getElementById("NoSelection").style.backgroundColor = "#FFFFFF" : o == 1 || (document.getElementById("DettagliAlunnoR#" + Right("00" + i, 2) + "#D" + Right("00" + r, 2)).style.display = "", document.getElementById("DettagliVuoto").style.display = "none", SelectTabber(1, "tabber")))
}

function EvidenziaRigaRCLA(n, t, i, r, u, f, e, o, s, h) {
    var d = document.getElementById("arDateRDOC").value, g = d.split("|"), y = document.getElementById("iMaxDay").value,
        b, k, w = document.getElementById("bAccessibility").value, a = document.getElementById("bSoloAssenze").value, c,
        l, v, p;
    if (h == undefined && (h = 0), b = 0, (r == undefined || r == 999) && (r = document.getElementById("iDaySelectedInRDOC").value), document.getElementById("iDaySelectedInRDOC").value != r && (document.getElementById("iDaySelectedInRDOC").value = r), h == "0") {
        for (c = 1; c <= y; c++) document.getElementById("GIORNO#" + c).style.color = "white";
        document.getElementById("GIORNO#" + r).style.color = "yellow"
    }
    if (w == 0 && a == 0 && (document.getElementById("DataSelezionataWeek").innerHTML = g[r - 1]), w == 0 && a == 0) {
        for (c = 1; c <= y; c++) document.getElementById("DettagliPersonali#" + Right("00" + c, 2)).style.display = "none", document.getElementById("DettagliAltriDocenti#" + Right("00" + c, 2)).style.display = "none";
        document.getElementById("DettagliPersonali#" + Right("00" + r, 2)).style.display = "";
        document.getElementById("DettagliAltriDocenti#" + Right("00" + r, 2)).style.display = ""
    }
    for (l = document.getElementById("idxAluDetail"), v = b; v <= t; v++) document.getElementById("RTAB#" + Right("00" + v, 2)).style.backgroundColor = "#FFFFFF";
    if (w == 0 && a == 0 && l && (l.src = ""), e == 1 && document.getElementById("AluSelectedInRDOC").value != "" && (i = document.getElementById("AluSelectedInRDOC").value, document.getElementById("AluSelectedInRDOC").value = ""), document.getElementById("AluSelectedInRDOC").value == "" && i != -1) document.getElementById("AluSelectedInRDOC").value = Right("00" + i, 2), document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFCBF", o == 1 || a == 1 || l && (k = "REREC_DettaglioAlunno.aspx?IROW=" + i + "&IDAY=" + r + "&chkValue=" + s, l.src = k, document.getElementById("DettagliVuoto").style.display = "none", SelectTabber(1, "tabber")); else if (document.getElementById("AluSelectedInRDOC").value == Right("00" + i, 2)) document.getElementById("AluSelectedInRDOC").value = "", document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFFFF", o == 1 || a == 1 || y == 1 && (l && (p = window.frames.idxAluDetail, p.postBackFromParent("SALVA", "")), document.getElementById("DettagliVuoto").style.display = "", document.getElementById("AluSelectedInRDOC").value = "", SelectTabber(0, "tabber")); else if (i != -1 && (document.getElementById("AluSelectedInRDOC").value = Right("00" + i, 2), document.getElementById("RTAB#" + Right("00" + i, 2)).style.backgroundColor = "#FFFCBF", o != 1 && a != 1 && y == 1)) {
        if (l) {
            p = window.frames.idxAluDetail;
            try {
                p.postBackFromParent("SALVA", i + "|" + r + "|" + s);
                document.getElementById("DettagliVuoto").style.display = "none";
                SelectTabber(1, "tabber")
            } catch (nt) {
                alert("Si Ã¨ verificato un errore si prega di ricaricare la pagina.")
            }
        }
        document.getElementById("DettagliVuoto").style.display = "none";
        SelectTabber(1, "tabber")
    }
}

function SalvaDettaglioAlunniLive() {
    var n = parent.document.getElementById("AluSelectedInRDOC").value,
        t = parent.document.getElementById("iDaySelectedInRDOC").value;
    __doPostBack("SALVA_LIVE", n + "|" + t + "|")
}

function SalvaDettaglioAlunni(n) {
    var i = document.getElementById("idxAluDetail"), t, r;
    if (i && (t = window.frames.idxAluDetail, t)) try {
        r = document.getElementById("iMaxAlu").value;
        n == undefined ? t.postBackFromParent("SALVA", "") : t.postBackFromParent("SALVA", "NoHide")
    } catch (u) {
        return
    }
}

function Parent_DoPostBack(n, t) {
    window.parent.__doPostBack(n, t)
}

function ResetLayer(n) {
    document.getElementById(n).src = "";
    document.getElementById(n).innerHTML = ""
}

function ResetParentLayer(n) {
    window.parent.document.getElementById(n).src = "";
    window.parent.document.getElementById(n).innerHTML = ""
}

function SalvaValutazioneCompetenze() {
    var n = bootbox.dialog({
        message: "Scegliendo: <br><br><ul><li><span class='label label-sm label-primary'>SÃ¬<\/span><small>, verranno riportate le medie degli obiettivi del giorno solo se nel registro del docente non sono presenti giÃ  dei voti nel giorno.<\/small><\/li><li><span class='label label-sm label-success'>Sovrascrivi<\/span><small>, eventuali valutazioni inserite nel registro del docente del giorno verranno modificate con le medie degli obiettivi del giorno che si sta salvando.<\/small><\/li><li><span class='label label-sm label-default'>No<\/span><small>, nessun voto verrÃ  riportato nel registro del docente.<\/small><\/li><\/ul>",
        title: "Scrittura medie su Registro del Docente",
        onEscape: function () {
        },
        buttons: {
            uno: {
                label: '<i class="far fa-copy"><\/i> SÃ¬', className: "btn-primary", callback: function () {
                    __doPostBack("SAVE_CON_MEDIE", "")
                }
            },
            due: {
                label: '<i class="fa fa-paste"><\/i> Sovrascrivi', className: "btn-success", callback: function () {
                    __doPostBack("SAVE_SOVRASCRIVI_MEDIE", "")
                }
            },
            tre: {
                label: '<i class="far fa-save"><\/i> No', className: "btn-default", callback: function () {
                    __doPostBack("SAVE_SENZA_MEDIE", "")
                }
            }
        }
    });
    n.css({
        top: "50%", "margin-top": function () {
            return -(n.height() / 2)
        }
    })
}

function EvaluateVoto(n, t) {
    var i;
    if (n.length == 0) return 0;
    var f = "", e, o, r = [], u = [];
    for (r[0] = "Â½", r[1] = "â…“", r[2] = "-", r[3] = "--", r[4] = "---", r[5] = "+", r[6] = "++", r[7] = "+++", r[8] = "=", u[0] = .5, u[1] = .33, u[2] = -.25, u[3] = -.5, u[4] = -.75, u[5] = .25, u[6] = .5, u[7] = .75, u[8] = -.5, i = 0; i <= n.length - 1; i++) (isNumber(n.substr(i, 1)) || n.substr(i, 1) == "." || n.substr(i, 1) == ",") && (f = f + n.substr(i, 1));
    for (f = f.replace(",", "."), o = +f, i = n.length - 1; i >= 0; i--) e = r.indexOf(n.substr(i, 1)), (e > 0 || e == 0) && (o += u[e]);
    return o > t || o == t ? 1 : -1
}

function GetDocFromIFrame(n) {
    return n.document || n.contentDocument || n.contentWindow.document
}

function startTimer(n) {
    timer.start();
    setTimeout(stopTimer, n)
}

function stopTimer() {
    timer.stop
}

function ShowContentFromJSON(n) {
    var t = JSON.parse(n), i = $("#" + t.content);
    i.html(t.html)
}

function AppelloAlunnoPresente(n) {
    var t = n.id.slice(-8);
    document.getElementById("AssenzaAlunno" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDAssenzaAlunno" + t).value = "0";
    document.getElementById("AssenzaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDConcorre" + t).value = "1";
    document.getElementById("Concorre" + t).src = "../images/Appello/Appello_concorre.png";
    document.getElementById("RitardoSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDRitardoSiNo" + t).value = "0";
    document.getElementById("RitardoOra" + t).disabled = !0;
    document.getElementById("RitardoOra" + t).value = "0";
    document.getElementById("RitardoOrario" + t).disabled = !0;
    document.getElementById("RitardoOrario" + t).value = "00:00";
    document.getElementById("RitardoGiustificatoSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("UscitaSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDUscitaSiNo" + t).value = "0";
    document.getElementById("UscitaOra" + t).disabled = !0;
    document.getElementById("UscitaOra" + t).value = "0";
    document.getElementById("UscitaOrario" + t).disabled = !0;
    document.getElementById("UscitaOrario" + t).value = "00:00";
    document.getElementById("UscitaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("RientroSiNo" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDRientroSiNo" + t).value = "0";
    document.getElementById("RientroOra" + t).disabled = !0;
    document.getElementById("RientroOra" + t).value = "0";
    document.getElementById("RientroOrario" + t).disabled = !0;
    document.getElementById("RientroOrario" + t).value = "00:00";
    document.getElementById("RientroGiustificatoSiNo" + t).src = "../images/Appello/Appello.png"
}

function AppelloToglieAssenza(n) {
    var t = n.id.slice(-8);
    document.getElementById("AssenzaAlunno" + t).src = "../images/Appello/Appello.png";
    document.getElementById("HIDAssenzaAlunno" + t).value = "0";
    document.getElementById("AssenzaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png"
}

function AppelloAlunnoAssente(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        n.src.indexOf("Appello.png") !== -1 ? (AppelloAlunnoPresente(n), n.src = "../images/Appello/Appello_assenza.png", document.getElementById("HIDAssenzaAlunno" + t).value = "1", document.getElementById("AssenzaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png") : AppelloAlunnoPresente(n);
        REOnChange()
    }
}

function AppelloGiustificaAssenza(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        document.getElementById("HIDAssenzaAlunno" + t).value === "1" ? (n.src = "../images/Appello/Appello_assenza_G.png", document.getElementById("HIDAssenzaAlunno" + t).value = "2") : document.getElementById("HIDAssenzaAlunno" + t).value === "2" && (n.src = "../images/Appello/Appello.png", document.getElementById("HIDAssenzaAlunno" + t).value = "1");
        REOnChange()
    }
}

function AppelloAlunnoRitardo(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        AppelloToglieAssenza(n);
        n.src.indexOf("Appello.png") !== -1 ? (n.src = "../images/Appello/Appello_ritardo.png", document.getElementById("HIDRitardoSiNo" + t).value = "1", document.getElementById("RitardoOra" + t).disabled = !1, document.getElementById("RitardoOrario" + t).disabled = !1, document.getElementById("RitardoOrario" + t).value = moment().format("HH:mm"), document.getElementById("RitardoGiustificatoSiNo" + t).src = "../images/Appello/Appello.png") : (n.src = "../images/Appello/Appello.png", document.getElementById("HIDRitardoSiNo" + t).value = "0", document.getElementById("RitardoOra" + t).disabled = !0, document.getElementById("RitardoOra" + t).value = "0", document.getElementById("RitardoOrario" + t).disabled = !0, document.getElementById("RitardoOrario" + t).value = "00:00", document.getElementById("RitardoGiustificatoSiNo" + t).src = "../images/Appello/Appello.png");
        REOnChange()
    }
}

function AppelloGiustificaRitardo(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        document.getElementById("HIDRitardoSiNo" + t).value === "1" ? (n.src = "../images/Appello/Appello_ritardo_G.png", document.getElementById("HIDRitardoSiNo" + t).value = "2") : document.getElementById("HIDRitardoSiNo" + t).value === "2" && (n.src = "../images/Appello/Appello.png", document.getElementById("HIDRitardoSiNo" + t).value = "1");
        REOnChange()
    }
}

function AppelloAlunnoUscita(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        AppelloToglieAssenza(n);
        n.src.indexOf("Appello.png") !== -1 ? (n.src = "../images/Appello/Appello_uscita.png", document.getElementById("HIDUscitaSiNo" + t).value = "1", document.getElementById("UscitaOra" + t).disabled = !1, document.getElementById("UscitaOrario" + t).disabled = !1, document.getElementById("UscitaOrario" + t).value = moment().format("HH:mm"), document.getElementById("UscitaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png") : (n.src = "../images/Appello/Appello.png", document.getElementById("HIDUscitaSiNo" + t).value = "0", document.getElementById("UscitaOra" + t).disabled = !0, document.getElementById("UscitaOra" + t).value = "0", document.getElementById("UscitaOrario" + t).disabled = !0, document.getElementById("UscitaOrario" + t).value = "00:00", document.getElementById("UscitaGiustificatoSiNo" + t).src = "../images/Appello/Appello.png");
        REOnChange()
    }
}

function AppelloGiustificaUscita(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        document.getElementById("HIDUscitaSiNo" + t).value === "1" ? (n.src = "../images/Appello/Appello_uscita_G.png", document.getElementById("HIDUscitaSiNo" + t).value = "2") : document.getElementById("HIDUscitaSiNo" + t).value === "2" && (n.src = "../images/Appello/Appello.png", document.getElementById("HIDUscitaSiNo" + t).value = "1");
        REOnChange()
    }
}

function AppelloAlunnoRientro(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        AppelloToglieAssenza(n);
        n.src.indexOf("Appello.png") !== -1 ? (n.src = "../images/Appello/Appello_rientro.png", document.getElementById("HIDRientroSiNo" + t).value = "1", document.getElementById("RientroOra" + t).disabled = !1, document.getElementById("RientroOrario" + t).disabled = !1, document.getElementById("RientroOrario" + t).value = moment().format("HH:mm"), document.getElementById("RientroGiustificatoSiNo" + t).src = "../images/Appello/Appello.png") : (n.src = "../images/Appello/Appello.png", document.getElementById("HIDRientroSiNo" + t).value = "0", document.getElementById("RientroOra" + t).disabled = !0, document.getElementById("RientroOra" + t).value = "0", document.getElementById("RientroOrario" + t).disabled = !0, document.getElementById("RientroOrario" + t).value = "00:00", document.getElementById("RientroGiustificatoSiNo" + t).src = "../images/Appello/Appello.png");
        REOnChange()
    }
}

function AppelloGiustificaRientro(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        document.getElementById("HIDRientroSiNo" + t).value === "1" ? (n.src = "../images/Appello/Appello_rientro_G.png", document.getElementById("HIDRientroSiNo" + t).value = "2") : document.getElementById("HIDRientroSiNo" + t).value === "2" && (n.src = "../images/Appello/Appello.png", document.getElementById("HIDRientroSiNo" + t).value = "1");
        REOnChange()
    }
}

function AppelloConcorreCalcolo(n) {
    if (document.getElementById("cLocked").value === "0") {
        var t = n.id.slice(-8);
        n.src.indexOf("images/Appello/Appello.png") !== -1 ? (n.src = "../images/Appello/Appello_concorre.png", document.getElementById("HIDConcorre" + t).value = "1") : (n.src = "../images/Appello/Appello.png", document.getElementById("HIDConcorre" + t).value = "0");
        REOnChange()
    }
}

function RCLAConcorreCalcolo(n) {
    var i = n.id, t;
    t = i.replace("Concorre", "HIDConcorre");
    document.getElementById(t).value = document.getElementById(chkValue).checked == !0 ? "1" : "0";
    REOnChange()
}

function EditTeamProgrammazione(n) {
    DoPostBack("EditTeam", n)
}

function DeleteTeamProgrammazione(n) {
    var t = confirm("Eliminare il Team selezionato?");
    t && DoPostBack("DeleteTeam", n)
}

function NewVerbaleProgrammazione(n) {
    DoPostBack("NewVerbale", n)
}

function EditVerbaleProgrammazione(n) {
    DoPostBack("EditVerbale", n)
}

function ViewVerbaleProgrammazione(n) {
    DoPostBack("ViewVerbale", n)
}

function DeleteVerbaleProgrammazione(n) {
    var t = confirm("Eliminare il verbale selezionato?");
    t && DoPostBack("DeleteVerbale", n)
}

function DeleteFileVerbaleProgrammazione(n, t) {
    var r = confirm("Eliminare il verbale selezionato?"), i;
    r && (ApplyWait(), i = "../Secret/APP_Ajax_Post.aspx?Action=DELETE_FILE_VERBALE", i = i + "&Others=" + n, $.ajax({
        type: "GET",
        cache: !1,
        url: i,
        async: !0,
        dataType: "html",
        success: function (n) {
            n == "" ? DoPostBack("RefreshVerbale", t) : (alert(n), HideWait())
        },
        error: function (n, t, i) {
            alert(i)
        }
    }))
}

function SaveVerbale() {
    var c = document.getElementById("NumeroVerbale").value, f, o, s, h, r, u, t, e, i, n;
    if (c == "") {
        alert("E'necessario indicare il numero di verbale.");
        return
    }
    if (f = document.getElementById("DataVerbale").value, f == "") {
        alert("E'necessario indicare la data del verbale.");
        return
    }
    if (o = document.getElementById("DescrizioneVerbale").value, o == "") {
        alert("E'necessario indicare una descrizione del verbale.");
        return
    }
    if (!ValidateDate(f)) {
        alert("E'necessario indicare la data del verbale.");
        return
    }
    if (s = document.getElementById("OraInizio").value, r = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(s), !r) {
        alert("E'necessario indicare l'orario di inizio della riunione.");
        return
    }
    if (h = document.getElementById("OraFine").value, r = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(h), !r) {
        alert("E'necessario indicare l'orario di fine della riunione.");
        return
    }
    if (ApplyWait(), u = $("#FileToUpload").get(0), !(u == undefined)) for (t = u.files, e = new FormData, i = 0; i < t.length; i++) e.append(t[i].name, t[i]);
    u == undefined || t.length == 0 ? DoPostBack("SaveVerbale", "") : (n = {}, n.url = "../../Handlers/SD_UploadHandler.ashx?UseGuid=true", n.type = "POST", n.data = e, n.contentType = !1, n.processData = !1, n.success = function (n) {
        document.getElementById("DATI_FILE").value = n;
        DoPostBack("SaveVerbale", "")
    }, n.error = function (n) {
        alert(n.statusText + "Non Ã¨ possibile salvare il verbale.")
    }, $.ajax(n))
}

function ShowVerbaliTeam(n) {
    var t = $(n).val();
    t !== "undefined" && (t === "-1" ? $("table[data-id-team]").show() : ($("table[data-id-team]").hide(), $('table[data-id-team="' + t + '"]').show()))
}

function ChangeTabSchedaAlunno(n) {
    var i, r, t;
    if (n.index == 0 || n.index == 2) {
        for (i = 0, r = document.getElementById("iMaxDay").value, t = i; t <= " & dstXML_RecA.Tables(0).Rows.Count - 1 & "; t++) document.getElementById("RTAB#" + Right("00" + t, 2)).style.backgroundColor = "#FFFFFF";
        document.getElementById("DettagliVuoto").style.display = "";
        document.getElementById("AluSelectedInRDOC").value = "";
        document.getElementById("idxAluDetail").src = ""
    }
}

function StampaLogComunicazione(n) {
    DoPostBack("PRINT", n);
    HideWait();
    theForm.__EVENTTARGET.value = "";
    theForm.__EVENTARGUMENT.value = ""
}

function StampaRegistro(n) {
    DoPostBack("PRINT", n);
    HideWait();
    theForm.__EVENTTARGET.value = "";
    theForm.__EVENTARGUMENT.value = ""
}

function setFontSize(n) {
    var t = document.getElementsByTagName("body")[0];
    n == "+" ? percentuale < maxPercentuale && (percentuale = percentuale + 20) : percentuale > minPercentuale && (percentuale = percentuale - 20);
    t.style.fontSize = percentuale + "%";
    bajb_backdetect.SafariHash = "false"
}

function parseVoto(n) {
    var t = 0, i, r, u, f;
    return n && n.trim() && (n = n.replace(",", "."), i = n.indexOf("/"), i > 0 && (r = parseFloat(n.substring(0, i)), u = parseFloat(n.substring(i + 1)), $.isNumeric(r) && $.isNumeric(u) && (t = (r + u) / 2)), t === 0 && (t = parseFloat(n), $.isNumeric(t) ? n.endsWith("%") && (t = t / 10) : t = 0), t === 0 && (f = n.replace("+", "").replace("-", ""), tableVoti.hasOwnProperty(f) && (t = tableVoti[f])), t > 0 && (n.indexOf("+") > 0 && (t += .25), n.indexOf("-") > 0 && (t -= .25), t > 999 ? t = 999 : t < 0 && (t = 0))), parseFloat(t.toFixed(2))
}

function parseDate(n) {
    var t = n.match(/(\d+)/g);
    return t == null || t.length != 3 ? null : new Date(t[2], t[1] - 1, t[0])
}

function detectIE() {
    var n = window.navigator.userAgent, i = n.indexOf("MSIE "), u, r, t;
    return i > 0 ? parseInt(n.substring(i + 5, n.indexOf(".", i)), 10) : (u = n.indexOf("Trident/"), u > 0) ? (r = n.indexOf("rv:"), parseInt(n.substring(r + 3, n.indexOf(".", r)), 10)) : (t = n.indexOf("Edge/"), t > 0) ? parseInt(n.substring(t + 5, n.indexOf(".", t)), 10) : !1
}

function attachSafariModalFix(n) {
    (function () {
        function c() {
            o = document.body.scrollTop;
            r = !0;
            f = setTimeout(h, 800);
            u && clearTimeout(u)
        }

        function l() {
            r = !1;
            u = setTimeout(v, 10);
            f && clearTimeout(f)
        }

        function a() {
            r && h()
        }

        function h() {
            e = parseInt(n.style.top) | 0;
            var t = -document.body.scrollTop + o + e;
            t = Math.max(-200, Math.min(200, t));
            o = 0;
            document.body.scrollTop = 0;
            n.style.top = t + "px"
        }

        function v() {
            document.body.scrollTop += -e;
            n.style.top = ""
        }

        var s = window.navigator.userAgent, r, u, f, i, t, e, o;
        if (s.match(/iPad/i) || s.match(/iPhone/i)) for (r = !1, i = n.querySelectorAll("input, textarea"), t = 0; t < i.length; t++) i[t].addEventListener("focus", c), i[t].addEventListener("blur", l), i[t].addEventListener("keyup", a)
    })()
}

var bSave = !1, BrowserDetect = {
    init: function () {
        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS"
    },
    searchString: function (n) {
        for (var i, r, t = 0; t < n.length; t++) if (i = n[t].string, r = n[t].prop, this.versionSearchString = n[t].versionSearch || n[t].identity, i) {
            if (i.indexOf(n[t].subString) != -1) return n[t].identity
        } else if (r) return n[t].identity
    },
    searchVersion: function (n) {
        var t = n.indexOf(this.versionSearchString);
        if (t != -1) return parseFloat(n.substring(t + this.versionSearchString.length + 1))
    },
    dataBrowser: [{string: navigator.userAgent, subString: "Chrome", identity: "Chrome"}, {
        string: navigator.userAgent,
        subString: "OmniWeb",
        versionSearch: "OmniWeb/",
        identity: "OmniWeb"
    }, {
        string: navigator.vendor,
        subString: "Apple",
        identity: "Safari",
        versionSearch: "Version"
    }, {prop: window.opera, identity: "Opera"}, {
        string: navigator.vendor,
        subString: "iCab",
        identity: "iCab"
    }, {string: navigator.vendor, subString: "KDE", identity: "Konqueror"}, {
        string: navigator.userAgent,
        subString: "Firefox",
        identity: "Firefox"
    }, {string: navigator.vendor, subString: "Camino", identity: "Camino"}, {
        string: navigator.userAgent,
        subString: "Netscape",
        identity: "Netscape"
    }, {
        string: navigator.userAgent,
        subString: "MSIE",
        identity: "Explorer",
        versionSearch: "MSIE"
    }, {
        string: navigator.userAgent,
        subString: "Gecko",
        identity: "Mozilla",
        versionSearch: "rv"
    }, {string: navigator.userAgent, subString: "Mozilla", identity: "Netscape", versionSearch: "Mozilla"}],
    dataOS: [{string: navigator.platform, subString: "Win", identity: "Windows"}, {
        string: navigator.platform,
        subString: "Mac",
        identity: "Mac"
    }, {string: navigator.userAgent, subString: "iPhone", identity: "iPhone/iPod"}, {
        string: navigator.platform,
        subString: "Linux",
        identity: "Linux"
    }]
}, giCursorDistance, giMousePositionX, giMousePositionY;
BrowserDetect.init();
giCursorDistance = 10;
$(function () {
    $(".hScroter1").scroll(function () {
        $(".hScroter2").scrollLeft($(".hScroter1").scrollLeft())
    });
    $(".hScroter2").scroll(function () {
        $(".hScroter1").scrollLeft($(".hScroter2").scrollLeft())
    })
});
$(window).load(function () {
    $(document).ready(function () {
        function n(n) {
            for (var t = [], r = ":", f = 2, u = 5, e = !0, u = Math.min(u, n.length), i = 0; i < u; i++) if (isNumber(n[i]) || n[i] == r && i == f) i == f && n[i] != r ? (t.push(r), t.push(parseInt(n[i]))) : t.push(n[i]); else {
                t[i] = "0";
                e = !1;
                break
            }
            switch (t.length) {
                case 1:
                    parseInt(t[0]) > 2 && (t[0] = "");
                    break;
                case 2:
                    parseInt(t[0]) == 2 && parseInt(t[1]) > 3 && (t[0] = "", t[1] = "");
                    break;
                case 4:
                    parseInt(t[3]) > 5 && (t[3] = "")
            }
            return t.join("")
        }

        function t(n) {
            switch (n.length) {
                case 0:
                    return "00:00";
                case 1:
                    return "0" + n + ":00";
                case 2:
                    return n + ":00";
                case 3:
                    return n + "00";
                case 4:
                    return n + "0";
                default:
                    return n
            }
        }

        $(".txtOrario").keyup(function (t) {
            t.keyCode != 37 & t.keyCode != 39 && $(this).val(n($(this).val()))
        });
        $(".txtOrario").change(function () {
            $(this).val(t($(this).val()))
        });
        $("input[type='text']").not(".form-control,.select2-input").change(function () {
            var n = $(this).val();
            n = RemoveCR(n);
            $(this).val(n);
            REOnChange()
        });
        $("input.detectTabDate").on("keydown", function (n) {
            if (n.keyCode == 9) {
                n.preventDefault();
                var t = $(this).attr("id"), i = Right(t, 2), r = Left(Right(t, 4), 1);
                szNextItem = "txtValue#" + r + "#" + Right("00" + parseInt(i), 2);
                setTimeout("document.getElementsByName('" + szNextItem + "')[0].focus()", 1)
            }
        });
        $("input.detectTabValori").on("keydown", function (n) {
            if (n.keyCode == 9) {
                n.preventDefault();
                var i = $(this).attr("id"), r = Right(i, 2), t = Left(i, 11);
                t = t + Right("00" + (parseInt(r) + 1), 2);
                setTimeout("document.getElementsByName('" + t + "')[0].focus()", 1)
            }
        });
        $("input.valueCheckAssenzeSSA").on("change", function () {
            document.body.style.cursor = "wait";
            isNumber(this.value) || (this.value = 0, alert("ATTENZIONE! - Il valore deve essere esclusivamente numerico."));
            document.body.style.cursor = "default"
        });
        $("input.ChechVoti_DEF").on("change", function () {
            var r = $(this).val().toUpperCase(), n = document.getElementById("ElencoIDVoti").value.toUpperCase(),
                u = n.split("Â§"), t = u.indexOf(r), i;
            if (t == -1) return alert("Il valore inserito non Ã¨ presente tra quelli della tabella voti."), $(this).val(""), RicalcoloMedia(), !1;
            n = document.getElementById("ElencoValoriVoti").value;
            i = n.split("Â§");
            this.style.color = i[t] >= 6 ? "#303030" : "red";
            RicalcoloMedia()
        });
        $("select.ChechVoti_DEF").on("change", function () {
            var r = $(this).val().toUpperCase(), n = document.getElementById("ElencoIDVoti").value.toUpperCase(),
                u = n.split("Â§"), t = u.indexOf(r), i;
            if (t == -1) return alert("Il valore inserito non Ã¨ presente tra quelli della tabella voti."), $(this).val(""), RicalcoloMedia(), !1;
            n = document.getElementById("ElencoValoriVoti").value;
            i = n.split("Â§");
            this.style.color = i[t] >= 6 ? "#303030" : "red";
            RicalcoloMedia()
        });
        $("input.CalcoloCredito").on("change", function () {
            RicalcolaCreditoTotale()
        });
        $("input.REG_DOC_CHANGE_VOTI_VALUE").on("change", null, function () {
            var i = $(this).val(), r, n, t;
            i = EvaluateVoto(i, 6);
            r = $(this).attr("name");
            n = r.split("#");
            t = "";
            n[2] == 4 && (t = " voto_altro");
            i == 0 ? (document.getElementById(r).className = "REG_DOC_CHANGE_VOTI_VALUE txtVoti_NoColor", document.getElementById("IDROW#" + Right("00" + n[1], 2) + "#IDCOL#" + Right("00" + n[2], 2) + "#IDAY#" + Right("00" + n[3], 2)).className = "no_voto" + t, REOnChange()) : (document.getElementById(r).className = "REG_DOC_CHANGE_VOTI_VALUE txtVoti_Color", document.getElementById("IDROW#" + Right("00" + n[1], 2) + "#IDCOL#" + Right("00" + n[2], 2) + "#IDAY#" + Right("00" + n[3], 2)).className = i == 1 ? "voto_positivo" + t : "voto_negativo" + t, REOnChange())
        });
        $("input.REG_COMP_CHANGE_VOTI_VALUE").on("change", function () {
            var i = $(this).val(), r, n, t;
            i = EvaluateVoto(i, 6);
            r = $(this).attr("name");
            n = r.split("#");
            t = "";
            n[2] == 4 && (t = " voto_altro");
            i == 0 ? (document.getElementById(r).className = "REG_COMP_CHANGE_VOTI_VALUE txtVoti_NoColor", document.getElementById("IDROW#" + Right("00" + n[1], 2) + "#IDCOL#" + Right("00" + n[2], 2) + "#IDAY#" + Right("00" + n[3], 2)).className = "no_voto" + t, REOnChange()) : (document.getElementById(r).className = "REG_COMP_CHANGE_VOTI_VALUE txtVoti_Color", document.getElementById("IDROW#" + Right("00" + n[1], 2) + "#IDCOL#" + Right("00" + n[2], 2)).className = i == 1 ? "voto_positivo" + t : "voto_negativo" + t, REOnChange())
        });
        $("input.REG_DOC_CHANGE_ORE_ASS_VALUE").on("change", function () {
            var t = $(this).val(), i = Right($(this).attr("name"), 2),
                n = document.getElementById("txtORELEZCLASSE#" + i).value;
            isNumber(t) ? $.trim(n) == "" || $.trim(n) == "0" ? REOnChange() : t > n ? (alert("Le ore di assenza non possono essere maggiori delle ore di lezione."), $(this).val("")) : REOnChange() : (alert("E' possibile inserire solo valori numerici in questo campo."), $(this).val(""))
        });
        $("input.REG_DOC_CHANGE_ORELEZIND_VALUE").on("change", function () {
            var n = $(this).val();
            if (!isNumber(n)) {
                alert("E' possibile inserire solo valori numerici in questo campo.");
                $(this).val("");
                return
            }
            REOnChange()
        });
        $("textarea.REG_DOC_CHANGE_IN_TEXTAREA").on("change", function () {
            REOnChange()
        });
        $("input.COMPETENZE_CHECKBOX_CHANGE_SELECTION").on("change", function () {
            var n = this.id;
            document.getElementById("txtCounter").value = document.all.item(n).checked == "checked" ? parseInt(document.getElementById("txtCounter").value) + 1 : parseInt(document.getElementById("txtCounter").value) - 1
        });
        $("input.CHECKBOX_CHANGE_GIUST_ASSENZA_RCLA_FROM_IFRAME").on("change", function () {
            var n = this.id;
            document.getElementById(n).checked == !0 ? (strKEY = n.replace("AssenteGiustificato", "HIDAssenzaAlunno"), parent.document.getElementById(strKEY).value = "2", document.getElementById(strKEY).value = "2") : (strKEY = n.replace("AssenteGiustificato", "HIDAssenzaAlunno"), parent.document.getElementById(strKEY).value = "1", document.getElementById(strKEY).value = "1");
            parent.UpdateFromIFrameBSave();
            REOnChange();
            SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_ASSENZA_RCLA_FROM_IFRAME").on("change", function (n) {
            ApplyWait();
            var t = this.id, i = 0, r = parent.document.getElementById("iMaxDay").value,
                u = parent.document.getElementById("bAccessibility").value,
                f = parent.document.getElementById("bSoloAssenze").value;
            if (r > 1 && (i = 1), document.getElementById(t).checked == !0) {
                if (strKEY = t.replace("AssenzaAlunno", "HIDAssenzaAlunno"), parent.document.getElementById(strKEY).value = "1", document.getElementById(strKEY).value = "1", u != 1 && f != 1) {
                    strKEY = t.replace("AssenzaAlunno", "HIDRitardoSiNo");
                    document.getElementById(strKEY).value = "0";
                    strKEY = t.replace("AssenzaAlunno", "HIDUscitaSiNo");
                    document.getElementById(strKEY).value = "0";
                    strKEY = t.replace("AssenzaAlunno", "HIDRientroSiNo");
                    document.getElementById(strKEY).value = "0";
                    parent.document.getElementById(t).checked = !0;
                    strKEY = t.replace("AssenzaAlunno", "SegnalazioneAssenza");
                    parent.document.getElementById(strKEY).className = "SegnalazioneAssenza_red";
                    strKEY = t.replace("AssenzaAlunno", "AssenteGiustificato");
                    document.getElementById(strKEY).disabled = !1;
                    strKEY = t.replace("AssenzaAlunno", "RitardoSiNo");
                    document.getElementById(strKEY).checked = !1;
                    strKEY = t.replace("AssenzaAlunno", "RitardoOra");
                    document.getElementById(strKEY).value = "0";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "RitardoOrario");
                    document.getElementById(strKEY).value = "00:00";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "RitardoGiustificatoSiNo");
                    document.getElementById(strKEY).checked = !1;
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "UscitaSiNo");
                    document.getElementById(strKEY).checked = !1;
                    strKEY = t.replace("AssenzaAlunno", "UscitaOra");
                    document.getElementById(strKEY).value = "0";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "UscitaOrario");
                    document.getElementById(strKEY).value = "00:00";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "UscitaGiustificatoSiNo");
                    document.getElementById(strKEY).checked = !1;
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "RientroSiNo");
                    document.getElementById(strKEY).checked = !1;
                    strKEY = t.replace("AssenzaAlunno", "RientroOra");
                    document.getElementById(strKEY).value = "0";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "RientroOrario");
                    document.getElementById(strKEY).value = "00:00";
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "RientroGiustificatoSiNo");
                    document.getElementById(strKEY).checked = !1;
                    document.getElementById(strKEY).disabled = !0;
                    strKEY = t.replace("AssenzaAlunno", "Concorre");
                    document.getElementById(strKEY).checked = !0;
                    try {
                        strKEY = t.replace("AssenzaAlunno", "MensaSiNo");
                        document.getElementById(strKEY).checked = !1
                    } catch (n) {
                        console.log(n)
                    }
                }
            } else parent.document.getElementById(t).checked = !1, strKEY = t.replace("AssenzaAlunno", "HIDAssenzaAlunno"), parent.document.getElementById(strKEY).value = "0", document.getElementById(strKEY).value = "0", strKEY = t.replace("AssenzaAlunno", "SegnalazioneAssenza"), parent.document.getElementById(strKEY).className = "SegnalazioneAssenza_white", strKEY = t.replace("AssenzaAlunno", "AssenteGiustificato"), document.getElementById(strKEY).checked = !1, document.getElementById(strKEY).disabled = !0;
            parent.UpdateFromIFrameBSave();
            REOnChange();
            SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_ASSENZA_RCLA").on("change", function (n) {
            var t, i;
            ApplyWait();
            var f = 0, r = this.id, s = document.getElementById("iMaxDay").value,
                u = document.getElementById("bAccessibility").value, o = document.getElementById("bSoloAssenze").value,
                h = document.getElementById("iMaxAlu").value, c = r.split("#"), e = c[1].replace("R", "");
            if (s > 1 && (f = 1), t = r.replace("AssenzaAlunno", "SegnalazioneAssenza"), u == 0 && document.getElementById("AluSelectedInRDOC").value != Right("00" + e, 2) && EvidenziaRigaRCLA(this, h, e, 999, 0, document.getElementById(r.replace("AssenzaAlunno", "IDCODALU")).value, 0, u, document.getElementById(r).checked), i = f == 0 && u == 0 ? document.getElementById("idxAluDetail") == null ? document : GetDocFromIFrame(document.getElementById("idxAluDetail")) : document, document.getElementById(r).checked == !0) {
                if (t = r.replace("AssenzaAlunno", "HIDAssenzaAlunno"), document.getElementById(t).value = "1", u == 1 || o == 1 || (t = r.replace("AssenzaAlunno", "SegnalazioneAssenza"), document.getElementById(t).className = "SegnalazioneAssenza_red"), f === 1) {
                    REOnChange();
                    HideWait();
                    return
                }
                try {
                    t = r.replace("AssenzaAlunno", "HIDRitardoSiNo");
                    i.getElementById(t).value = "0";
                    t = r.replace("AssenzaAlunno", "HIDUscitaSiNo");
                    i.getElementById(t).value = "0";
                    t = r.replace("AssenzaAlunno", "HIDRientroSiNo");
                    i.getElementById(t).value = "0";
                    t = r.replace("AssenzaAlunno", "HIDAssenzaAlunno");
                    i.getElementById(t).value = "1";
                    i.getElementById(r).checked = !0;
                    u == 0 && o == 0 && (t = r.replace("AssenzaAlunno", "AssenteGiustificato"), i.getElementById(t).disabled = !1);
                    t = r.replace("AssenzaAlunno", "RitardoSiNo");
                    i.getElementById(t).checked = !1;
                    t = r.replace("AssenzaAlunno", "RitardoOra");
                    i.getElementById(t).value = "0";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "RitardoOrario");
                    i.getElementById(t).value = "00:00";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "RitardoGiustificatoSiNo");
                    i.getElementById(t).checked = !1;
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "UscitaSiNo");
                    i.getElementById(t).checked = !1;
                    t = r.replace("AssenzaAlunno", "UscitaOra");
                    i.getElementById(t).value = "0";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "UscitaOrario");
                    i.getElementById(t).value = "00:00";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "UscitaGiustificatoSiNo");
                    i.getElementById(t).checked = !1;
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "RientroSiNo");
                    i.getElementById(t).checked = !1;
                    t = r.replace("AssenzaAlunno", "RientroOra");
                    i.getElementById(t).value = "0";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "RientroOrario");
                    i.getElementById(t).value = "00:00";
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "RientroGiustificatoSiNo");
                    i.getElementById(t).checked = !1;
                    i.getElementById(t).disabled = !0;
                    t = r.replace("AssenzaAlunno", "Concorre");
                    i.getElementById(t).checked = !0;
                    t = r.replace("AssenzaAlunno", "MensaSiNo");
                    i.getElementById(t).checked = !1
                } catch (n) {
                    console.log(n)
                }
            } else {
                t = r.replace("AssenzaAlunno", "HIDAssenzaAlunno");
                document.getElementById(t).value = "0";
                t = r.replace("AssenzaAlunno", "SegnalazioneAssenza");
                try {
                    document.getElementById(t).className = "SegnalazioneAssenza_white";
                    t = r.replace("AssenzaAlunno", "HIDAssenzaAlunno");
                    i.getElementById(t).value = "0";
                    i.getElementById(r).checked = !1;
                    t = r.replace("AssenzaAlunno", "AssenteGiustificato");
                    i.getElementById(t).checked = !1;
                    i.getElementById(t).disabled = !0
                } catch (n) {
                }
            }
            REOnChange();
            u == 0 && document.getElementById("AluSelectedInRDOC").value != Right("00" + e, 2) || HideWait()
        });
        $("input.CHECKBOX_CHANGE_RITARDO_RCLA").on("change", function () {
            var u = 0, t = this.id, f = window.parent.document.getElementById("iMaxDay").value,
                i = window.parent.document.getElementById("bAccessibility").value,
                r = window.parent.document.getElementById("bSoloAssenze").value, n;
            f > 1 && (u = 1);
            n = t.replace("RitardoSiNo", "SegnalazioneAssenza");
            document.getElementById(t).checked == !0 ? (i == 1 || r == 1 || (n = t.replace("RitardoSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_yellow"), n = t.replace("RitardoSiNo", "HIDRitardoSiNo"), document.getElementById(n).value = "1", n = t.replace("RitardoSiNo", "AssenzaAlunno"), window.parent.document.getElementById(n).checked = !1, n = t.replace("RitardoSiNo", "RitardoOra"), document.getElementById(n).disabled = !1, n = t.replace("RitardoSiNo", "RitardoOrario"), document.getElementById(n).disabled = !1, n = t.replace("RitardoSiNo", "RitardoGiustificatoSiNo"), document.getElementById(n).disabled = !1, n = t.replace("RitardoSiNo", "AssenzaAlunno"), document.getElementById(n).checked = !1, i == 0 && r == 0 && (n = t.replace("RitardoSiNo", "AssenteGiustificato"), document.getElementById(n).checked = !1, document.getElementById(n).disabled = !0), n = t.replace("RitardoSiNo", "HIDAssenzaAlunno"), parent.document.getElementById(n).value = "0", document.getElementById(n).value = "0") : (i == 0 && r == 0 && (n = t.replace("RitardoSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_white"), n = t.replace("RitardoSiNo", "HIDRitardoSiNo"), document.getElementById(n).value = "0", n = t.replace("RitardoSiNo", "RitardoOra"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "0", n = t.replace("RitardoSiNo", "RitardoOrario"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "00:00", n = t.replace("RitardoSiNo", "RitardoGiustificatoSiNo"), document.getElementById(n).disabled = !0, document.getElementById(n).checked = !1);
            parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && r == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_USCITA_RCLA").on("change", function () {
            var u = 0, t = this.id, f = window.parent.document.getElementById("iMaxDay").value,
                i = window.parent.document.getElementById("bAccessibility").value,
                r = window.parent.document.getElementById("bSoloAssenze").value, n;
            f > 1 && (u = 1);
            n = t.replace("UscitaSiNo", "SegnalazioneAssenza");
            document.getElementById(t).checked == !0 ? (i == 1 || r == 1 || (n = t.replace("UscitaSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_yellow"), n = t.replace("UscitaSiNo", "HIDUscitaSiNo"), document.getElementById(n).value = "1", n = t.replace("UscitaSiNo", "AssenzaAlunno"), window.parent.document.getElementById(n).checked = !1, n = t.replace("UscitaSiNo", "UscitaOra"), document.getElementById(n).disabled = !1, n = t.replace("UscitaSiNo", "UscitaOrario"), document.getElementById(n).disabled = !1, n = t.replace("UscitaSiNo", "UscitaGiustificatoSiNo"), document.getElementById(n).disabled = !1, n = t.replace("UscitaSiNo", "AssenzaAlunno"), document.getElementById(n).checked = !1, i == 0 && r == 0 && (n = t.replace("UscitaSiNo", "AssenteGiustificato"), document.getElementById(n).checked = !1, document.getElementById(n).disabled = !0), n = t.replace("UscitaSiNo", "HIDAssenzaAlunno"), parent.document.getElementById(n).value = "0", document.getElementById(n).value = "0") : (i == 0 && r == 0 && (n = t.replace("UscitaSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_white"), n = t.replace("UscitaSiNo", "HIDUscitaSiNo"), document.getElementById(n).value = "0", n = t.replace("UscitaSiNo", "UscitaOra"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "0", n = t.replace("UscitaSiNo", "UscitaOrario"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "00:00", n = t.replace("UscitaSiNo", "UscitaGiustificatoSiNo"), document.getElementById(n).disabled = !0, document.getElementById(n).checked = !1);
            parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && r == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_RIENTRO_RCLA").on("change", function () {
            var u = 0, t = this.id, f = window.parent.document.getElementById("iMaxDay").value,
                i = window.parent.document.getElementById("bAccessibility").value,
                r = window.parent.document.getElementById("bSoloAssenze").value, n;
            f > 1 && (u = 1);
            n = t.replace("RientroSiNo", "SegnalazioneAssenza");
            document.getElementById(t).checked == !0 ? (i == 1 || r == 1 || (n = t.replace("RientroSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_yellow"), n = t.replace("RientroSiNo", "HIDRientroSiNo"), document.getElementById(n).value = "1", n = t.replace("RientroSiNo", "AssenzaAlunno"), window.parent.document.getElementById(n).checked = !1, n = t.replace("RientroSiNo", "RientroOra"), document.getElementById(n).disabled = !1, n = t.replace("RientroSiNo", "RientroOrario"), document.getElementById(n).disabled = !1, n = t.replace("RientroSiNo", "RientroGiustificatoSiNo"), document.getElementById(n).disabled = !1, n = t.replace("RientroSiNo", "AssenzaAlunno"), document.getElementById(n).checked = !1, i == 0 && r == 0 && (n = t.replace("RientroSiNo", "AssenteGiustificato"), document.getElementById(n).checked = !1, document.getElementById(n).disabled = !0), n = t.replace("RientroSiNo", "HIDAssenzaAlunno"), parent.document.getElementById(n).value = "0", document.getElementById(n).value = "0") : (i == 0 && r == 0 && (n = t.replace("RientroSiNo", "SegnalazioneAssenza"), window.parent.document.getElementById(n).className = "SegnalazioneAssenza_white"), n = t.replace("RientroSiNo", "HIDRientroSiNo"), document.getElementById(n).value = "0", n = t.replace("RientroSiNo", "RientroOra"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "0", n = t.replace("RientroSiNo", "RientroOrario"), document.getElementById(n).disabled = !0, document.getElementById(n).value = "00:00", n = t.replace("RientroSiNo", "RientroGiustificatoSiNo"), document.getElementById(n).disabled = !0, document.getElementById(n).checked = !1);
            parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && r == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_GIUST_RITARDO_RCLA_FROM_IFRAME").on("change", function () {
            var n = this.id, t = window.parent.document.getElementById("bSoloAssenze").value,
                i = window.parent.document.getElementById("bAccessibility").value;
            document.getElementById(n).checked == !0 ? (strKEY = n.replace("RitardoGiustificatoSiNo", "HIDRitardoSiNo"), document.getElementById(strKEY).value = "2") : (strKEY = n.replace("RitardoGiustificatoSiNo", "HIDRitardoSiNo"), document.getElementById(strKEY).value = "1");
            i == 0 && t == 0 && parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && t == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_GIUST_USCITA_RCLA_FROM_IFRAME").on("change", function () {
            var n = this.id, t = window.parent.document.getElementById("bSoloAssenze").value,
                i = window.parent.document.getElementById("bAccessibility").value;
            document.getElementById(n).checked == !0 ? (strKEY = n.replace("UscitaGiustificatoSiNo", "HIDUscitaSiNo"), document.getElementById(strKEY).value = "2") : (strKEY = n.replace("UscitaGiustificatoSiNo", "HIDUscitaSiNo"), document.getElementById(strKEY).value = "1");
            i == 0 && t == 0 && parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && t == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.CHECKBOX_CHANGE_GIUST_RIENTRO_RCLA_FROM_IFRAME").on("change", function () {
            var n = this.id, t = window.parent.document.getElementById("bSoloAssenze").value,
                i = window.parent.document.getElementById("bAccessibility").value;
            document.getElementById(n).checked == !0 ? (strKEY = n.replace("RientroGiustificatoSiNo", "HIDRientroSiNo"), document.getElementById(strKEY).value = "2") : (strKEY = n.replace("RientroGiustificatoSiNo", "HIDRientroSiNo"), document.getElementById(strKEY).value = "1");
            i == 0 && t == 0 && parent.UpdateFromIFrameBSave();
            REOnChange();
            i == 0 && t == 0 && SalvaDettaglioAlunniLive()
        });
        $("input.REG_RCLA_CHANGE_ORE_VALUE").on("change", function () {
            var n = $(this).val(), t = parent.document.getElementById("iMaxOreLez").value;
            isNumber(n) ? (parseInt(n) > parseInt(t) || parseInt(n) < 0) && (alert("E' possibile inserire solo valori numerici incompresi tra 0 e " + t + "."), $(this).val("0"), document.getElementById($(this).attr("id")).value = "0") : (alert("E' possibile inserire solo valori numerici in questo campo."), $(this).val("0"));
            parent.UpdateFromIFrameBSave();
            REOnChange()
        });
        $("#QuanteOre").unbind("change").change(function (n) {
            var r, u, t, i;
            for (n.preventDefault(), r = $(this).val(), u = $(this).data("maxore"), t = 2; t <= 8; t++) document.getElementById("cmbOra#O" + Right("00" + t, 2)).value = "", document.getElementById("cmbNomeCognome#O" + Right("00" + t, 2)).value = "", document.getElementById("cmbMateria#O" + Right("00" + t, 2)).value = "", document.getElementById("cmbTipo#O" + Right("00" + t, 2)).value = "", document.getElementById("chkFirmaNEW#O" + Right("00" + t, 2)).checked = "";
            for (document.getElementById("chkFirmaNEW#O" + Right("001", 2)).checked = "checked", i = document.getElementById("cmbOra#O" + Right("001", 2)).value, t = 2; t <= r; t++) i = parseInt(i) + 1, i <= u && (document.getElementById("cmbOra#O" + Right("00" + t, 2)).value = i, document.getElementById("cmbNomeCognome#O" + Right("00" + t, 2)).value = document.getElementById("cmbNomeCognome#O01").value, document.getElementById("cmbMateria#O" + Right("00" + t, 2)).value = document.getElementById("cmbMateria#O01").value, document.getElementById("cmbTipo#O" + Right("00" + t, 2)).value = document.getElementById("cmbTipo#O01").value, document.getElementById("chkFirmaNEW#O" + Right("00" + t, 2)).checked = "checked", RetrieveDataFromDropDownList(t), CheckMatForDoc(t))
        })
    })
});
var percentuale = 100, maxPercentuale = 200, minPercentuale = 80;
var iStackModal = 0, Base64 = {
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", encode: function (n) {
        var f = "", e, t, i, s, h, o, r, u = 0;
        for (n = Base64._utf8_encode(n); u < n.length;) e = n.charCodeAt(u++), t = n.charCodeAt(u++), i = n.charCodeAt(u++), s = e >> 2, h = (e & 3) << 4 | t >> 4, o = (t & 15) << 2 | i >> 6, r = i & 63, isNaN(t) ? o = r = 64 : isNaN(i) && (r = 64), f = f + this._keyStr.charAt(s) + this._keyStr.charAt(h) + this._keyStr.charAt(o) + this._keyStr.charAt(r);
        return f
    }, decode: function (n) {
        var t = "", e, o, s, h, u, r, f, i = 0;
        for (n = n.replace(/[^A-Za-z0-9\+\/\=]/g, ""); i < n.length;) h = this._keyStr.indexOf(n.charAt(i++)), u = this._keyStr.indexOf(n.charAt(i++)), r = this._keyStr.indexOf(n.charAt(i++)), f = this._keyStr.indexOf(n.charAt(i++)), e = h << 2 | u >> 4, o = (u & 15) << 4 | r >> 2, s = (r & 3) << 6 | f, t = t + String.fromCharCode(e), r != 64 && (t = t + String.fromCharCode(o)), f != 64 && (t = t + String.fromCharCode(s));
        return Base64._utf8_decode(t)
    }, _utf8_encode: function (n) {
        var i, r, t;
        for (n = n.replace(/\r\n/g, "\n"), i = "", r = 0; r < n.length; r++) t = n.charCodeAt(r), t < 128 ? i += String.fromCharCode(t) : t > 127 && t < 2048 ? (i += String.fromCharCode(t >> 6 | 192), i += String.fromCharCode(t & 63 | 128)) : (i += String.fromCharCode(t >> 12 | 224), i += String.fromCharCode(t >> 6 & 63 | 128), i += String.fromCharCode(t & 63 | 128));
        return i
    }, _utf8_decode: function (n) {
        for (var r = "", t = 0, i = c1 = c2 = 0; t < n.length;) i = n.charCodeAt(t), i < 128 ? (r += String.fromCharCode(i), t++) : i > 191 && i < 224 ? (c2 = n.charCodeAt(t + 1), r += String.fromCharCode((i & 31) << 6 | c2 & 63), t += 2) : (c2 = n.charCodeAt(t + 1), c3 = n.charCodeAt(t + 2), r += String.fromCharCode((i & 15) << 12 | (c2 & 63) << 6 | c3 & 63), t += 3);
        return r
    }
}, tableVoti = {};
String.prototype.replaceAll = function (n, t) {
    var i = this;
    return i.split(n).join(t)
}, function (n) {
    n.fn.serializeObject = function () {
        "use strict";
        var t = {}, i = function (i, r) {
            var u = t[r.name];
            "undefined" != typeof u && u !== null ? n.isArray(u) ? u.push(r.value) : t[r.name] = [u, r.value] : t[r.name] = r.value
        };
        return n.each(this.serializeArray(), i), t
    }
}(jQuery);
var DownloadFileConHandler = function () {
    return {
        DownloadFile: function () {
            $(".btn-download").unbind("click").click(function () {
                var r = "../../Handlers/SD_UploadDownloadHandler.aspx", u = $(this).data("cf"),
                    n = $(this).data("file-id"), t, i;
                n === undefined && (n = "");
                t = $(this).data("file-ref");
                i = $(this).data("file-name");
                /Android|webOS|iPhone|iPad|iPod|Opera Mini/i.test(navigator.userAgent) ? window.open(r + "?CustomerID=" + u + "&id=" + n + "&file=" + t + "&SourceFileName=" + encodeURIComponent(i), "_blank") : window.location = r + "?CustomerID=" + u + "&id=" + n + "&file=" + t + "&SourceFileName=" + encodeURIComponent(i)
            })
        }, exportStampa: function (n, t) {
            ApplyWait();
            $.ajax({
                type: "POST", cache: !1, url: n, data: t, async: !0, datatype: "json", success: function (n) {
                    HideWait();
                    var t = "../../Handlers/SD_UploadDownloadHandler.aspx";
                    t += "?CustomerID=" + n.cf + "&file=" + n.fileRef + "&SourceFileName=" + encodeURIComponent(n.fileName) + "&path=" + n.filePath + "&faction=0001";
                    /Android|webOS|iPhone|iPad|iPod|Opera Mini/i.test(navigator.userAgent) ? window.open(t, "_blank") : window.location = t
                }, error: function (n, t, i) {
                    HideWait();
                    SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                }
            })
        }
    }
}(), SweetMessage = function () {
    return {
        error: function (n, t) {
            t === undefined && (t = "");
            swal({title: t, text: n, type: "error"})
        }, errorCallBack: function (n, t) {
            swal({text: n, type: "error"}).then(function () {
                t()
            }, function () {
            })
        }, success: function (n) {
            swal({html: n, type: "success"})
        }, confirm: function (n, t) {
            swal({
                text: n,
                type: "question",
                showCancelButton: !0,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;SÃ¬",
                cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;No"
            }).then(function () {
                t()
            }, function () {
            })
        }
    }
}(), Notific8 = function () {
    return {
        success: function (n) {
            notific8(n, {
                life: "3000",
                color: "lime",
                sticky: !1,
                horizontalEdge: "top",
                verticalEdge: "right",
                image: "../images/white/icons8-about-48.png",
                zindex: 2001
            })
        }
    }
}(), handleInputField = function () {
    return {
        init: function (n, t, i, r, u, f) {
            if (n && $(".date-picker").datetimepicker({
                format: "DD/MM/YYYY",
                locale: "it",
                showTodayButton: !0,
                calendarWeeks: !0
            }), t && $(".time-picker").datetimepicker({
                format: "HH:mm",
                stepping: 5,
                locale: "it"
            }), r && ($(".switch-mini").bootstrapSwitch({
                size: "mini",
                onText: "SI",
                offText: "NO",
                onColor: "success",
                offColor: "danger"
            }), $(".switch").bootstrapSwitch({
                size: "small",
                onText: "SI",
                offText: "NO",
                onColor: "success",
                offColor: "danger"
            })), i && $(".select2").select2(), u && $(".maxlength").maxlength({
                alwaysShow: !1,
                threshold: 10,
                warningClass: "label label-warning",
                limitReachedClass: "label label-danger",
                message: "Usati %charsTyped% caratteri di %charsTotal%."
            }), f) {
                $(".form-control").removeAttr("changed");
                $(".form-control").on("input", function () {
                    $(this).attr("changed", "true");
                    REOnChange()
                })
            }
        }
    }
}(), handleModal = function () {
    var n = function (n) {
        var i, t;
        iStackModal += 1;
        i = $("#modal-content-dinamico");
        i.append("<div id='modal-stack-" + iStackModal + "' class='modal fade' tabindex='-1'><\/div>");
        t = $("#modal-stack-" + iStackModal);
        setTimeout(function () {
            t.html(n);
            t.modal({backdrop: "static"});
            HideWait()
        }, 100);
        t.on("hidden.bs.modal", function () {
            var n = $("#modal-stack-" + iStackModal);
            n.remove();
            iStackModal -= 1
        })
    };
    return {
        openModal: function (t) {
            n(t)
        }
    }
}(), Ajx = function () {
    return {
        getOpenModal: function (n, t) {
            ApplyWait();
            $.ajax({
                type: "GET", cache: !1, url: n, async: !0, datatype: "html", success: function (n) {
                    handleModal.openModal(n);
                    t && $("#modal-stack-" + iStackModal).css({
                        top: "50%", "margin-top": function () {
                            return -($("#modal-stack-" + iStackModal).height() / 2)
                        }
                    })
                }, error: function (n, t, i) {
                    HideWait();
                    SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                }
            })
        }
    }
}(), Generale = function () {
    function n() {
        $.ajax({
            type: "GET",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=UPDATE_COUNTER",
            async: !0,
            dataType: "json",
            success: function (n) {
                if (n.errorcode === "0") {
                    var t = JSON.parse(n.json);
                    $("#notifyBadge").html(t.iNotifications === "0" ? "" : t.iNotifications);
                    $("#messageBadge").html(t.iMessages === "0" ? "" : t.iMessages)
                } else $("#notifyBadge").html("~"), $("#messageBadge").html("~")
            },
            error: function () {
                $("#notifyBadge").html("err");
                $("#messageBadge").html("err")
            }
        })
    }

    return {
        initTimer: function () {
            setInterval(function () {
                $.ajax({
                    type: "GET",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Get.aspx?Action=READ_TIME",
                    async: !0,
                    dataType: "html",
                    success: function (n) {
                        $("#lblOnTimer").val("Data e ora: " + n)
                    },
                    error: function () {
                    }
                })
            }, 18e4)
        }, changePassword: function (n) {
            var t = "../Secret/APP_Ajax_Get.aspx?Action=CHANGE_PASSWORD&Others=" + n;
            Ajx.getOpenModal(t, !0)
        }, acceptPrivacy: function () {
            swal({
                text: "<p class='text-left' style='font-size: 16px'>Gentile Cliente<br>sappiamo che stai giÃ  ricevendo molte mail per gli aggiornamenti delle norme sulla protezione dei dati personali, ma dobbiamo inviarne una anche noi!<br>A partire dal 25 maggio 2018 Ã¨ efficace il nuovo Regolamento Europeo n. 2016/679 (â€œGDPRâ€) sulla protezione dei dati personali.<br>Axios Ã¨ da sempre attenta alla corretta gestione dei dati personali dei propri clienti. Ci impegniamo quotidianamente a proteggere la sicurezza delle informazioni ispirandoci ai piÃ¹ elevati standard di qualitÃ . In linea con il GDPR e nel rispetto del principio di trasparenza, abbiamo aggiornato la nostra informativa privacy di cui ti invitiamo a prendere visione. Sarai inviato alla pagina del tuo profilo per poter verificare lâ€™informativa ed accettare le condizioni di utilizzo. Non preoccuparti, non abbiamo effettuato nessuna modifica alle modalitÃ  di utilizzo dei tuoi dati personali. Semplicemente condivideremo con te maggiori informazioni utili per comprendere come gestiamo i tuoi dati che per noi sono un patrimonio da difendere e tutelare.<br>Ti invitiamo pertanto a compilare l'informativa nella sezione Privacy di Scuola Digitale per continuare ad utilizzare i nostri software nel pieno rispetto del trattamento dei tuoi dati personali.<br>Qualora volessi contattarci,  abbiamo dedicato una casella mail alle eventuali domande sulla nostra gestione della tua privacy: privacy@axiositalia.com<br><br>Cordiali Saluti,<br>Axios Italia Service S.r.l<\/p>",
                type: "warning",
                customClass: "swal-wide"
            }).then(function () {
                $("#imgSD").click()
            }, function () {
                $("#imgSD").click()
            })
        }, initImproveTheKing: function () {
            $("#btnImproveTheKing").unbind("click").click(function (n) {
                n.preventDefault();
                var t = "../Secret/APP_Ajax_Get.aspx?Action=IMPROVE_THE_KING&Others=" + $("#ContentPlaceHolderBody_txtAluSelected").val();
                Ajx.getOpenModal(t, !1)
            })
        }, initImproveTheKingModal: function () {
            $("#fsOggetto").unbind("change").change(function () {
                $(this).val() === "0" ? $("#improveTheKing").show() : $("#improveTheKing").hide()
            });
            $(".improve-the-king-invia").unbind("click").click(function (n) {
                var t, i, r, u, f;
                (n.preventDefault(), t = $("#form-request"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i !== undefined && (r = $(this).attr("href") + "?Action=" + i, r += "&Others=" + $("#ContentPlaceHolderBody_txtAluSelected").val(), u = t.serializeObject(), f = JSON.stringify(u), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    data: f,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                            var t = $("#modal-stack-" + iStackModal);
                            t.modal("hide");
                            Notific8.success("Invio eseguito correttamente.")
                        }
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            })
        }, initChangePassword: function () {
            $("#btnChangePassword").unbind("click").click(function (n) {
                n.preventDefault();
                Generale.changePassword(!1)
            })
        }, initChangePasswordModal: function () {
            $(".change-password-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), $("#change-password-error").html(""), t = $("#form-password"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") $("#change-password-error").html("<div class='alert alert-danger'>" + n.errormsg + "<\/div>"); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                Notific8.success("Password modificata correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            $("#change-password-error").html("<div class='alert alert-danger'>Errore durante la chiamata al Server: " + i.toString() + "<\/div>")
                        }
                    })
                }
            })
        }, initScuolaDigitaleSSO: function () {
            $(".SSO-SD").unbind("click").click(function (n) {
                var t, e;
                n.preventDefault();
                var u = $(this).data("action"), i = $(this).data("href") + "?Action=" + u, f = $(this).data("app-name"),
                    o = $(this).data("app-url"), r = $(this).data("others");
                r === undefined && (r = "");
                t = {};
                t.appurl = o;
                t.others = r;
                e = JSON.stringify(t);
                u !== undefined && f !== undefined && (i = i + "&App=" + f, ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: i,
                    async: !0,
                    data: e,
                    success: function (n) {
                        var t = document.createElement("form"), i, r;
                        t.target = n.target;
                        t.method = "POST";
                        t.action = n.url;
                        i = document.createElement("input");
                        i.type = "hidden";
                        i.name = "parameters";
                        i.value = n.parameters;
                        t.appendChild(i);
                        r = document.createElement("input");
                        r.type = "hidden";
                        r.name = "action";
                        r.value = n.action;
                        t.appendChild(r);
                        document.body.appendChild(t);
                        t.submit()
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Impossibile lanciare l'applicazione: " + i.toString())
                    }
                }))
            })
        }, initNotifyCounter: function () {
            n();
            setInterval(function () {
                n()
            }, 15e4)
        }
    }
}(), PannelloDirigente = function () {
    function n() {
        var t, i = "01/09/" + $("#asAnno").val(), n = parseInt($("#int3Value").val()) || 0;
        n > 0 ? (t = DateDiff(Today(), n), $("#st_sim_o").val(t)) : $("#st_sim_o").val(i);
        n = parseInt($("#int4Value").val()) || 0;
        n > 0 ? (t = DateDiff(Today(), n), $("#st_sim_sg").val(t)) : $("#st_sim_sg").val(i);
        n = parseInt($("#int6Value").val()) || 0;
        n >= 0 ? (t = DateDiff(Today(), n), $("#st_sim_o_tut").val(t)) : $("#st_sim_o_tut").val(i);
        n = parseInt($("#int7Value").val()) || 0;
        n >= 0 ? (t = DateDiff(Today(), n), $("#st_sim_sg_tut").val(t)) : $("#st_sim_sg_tut").val(i);
        n = parseInt($("#int5Value").val()) || 0;
        t = DateDiff(Today(), n);
        $("#st_sim_rcla").val(t)
    }

    function t(n, t) {
        ApplyWait();
        $.ajax({
            type: "POST",
            cache: !1,
            url: n,
            data: t,
            dataType: "json",
            contentType: "json",
            async: !0,
            success: function (n) {
                HideWait();
                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (setSaveFlag(!1), Notific8.success("Operazione eseguita correttamente."))
            },
            error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    function i() {
        var n = $("#content-calendario");
        $.ajax({
            type: "GET",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=PANNELLO_DIRIGENTE_CALENDARIO_REFRESH",
            async: !0,
            datatype: "html",
            success: function (t) {
                n.html(t)
            },
            error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    function r() {
        var n = $("#votTab");
        $.ajax({
            type: "GET",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=PANNELLO_DIRIGENTE_VOTI",
            async: !0,
            datatype: "html",
            success: function (t) {
                n.html(t)
            },
            error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    return {
        init: function () {
            $(document).ready(function () {
                handleInputField.init(!0, !1, !1, !1, !1);
                $("#iMedie").slider({
                    formatter: function (n) {
                        return Array.isArray(n) ? "1a: " + n[0] + "% - 2a: " + (parseInt(n[1]) - parseInt(n[0])) + "% - 3a: " + (100 - parseInt(n[1])) + "%" : n
                    }
                });
                n();
                var t = $("[id*=TabName]").val() !== "" ? $("[id*=TabName]").val() : "genTab";
                $('.nav-tabs a[href="#' + t + '"]').tab("show");
                $(".nav-tabs a").on("shown.bs.tab", function () {
                    $("[id*=TabName]").val($(this).attr("href").replace("#", ""))
                });
                $("#mailSign").val(Base64.decode($("#mailSign").val()));
                $("#mailSign").summernote({
                    height: 120,
                    lang: "it-IT",
                    toolbar: [["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]]
                })
            });
            $("#ContentPlaceHolderBody_divContent").on("shown.bs.tab", function (n) {
                var t = $($(n.target).attr("href")), i, r;
                t.html() === "" && (i = t.data("action"), i !== undefined && (r = t.attr("href") + "?Action=" + i, ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: r,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        t.html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                })))
            });
            $("input.aggiorna-riepilogo").on("input", function () {
                n()
            });
            $(".collapse").on("shown.bs.collapse", function () {
                $(this).parent().find(".fa-plus").removeClass("fa-plus").addClass("fa-minus")
            }).on("hidden.bs.collapse", function () {
                $(this).parent().find(".fa-minus").removeClass("fa-minus").addClass("fa-plus")
            });
            $(".pannello-dirigente-impostazioni-salva").unbind("click").click(function (n) {
                var u, r, i, f;
                n.preventDefault();
                u = $(this).data("action");
                u !== undefined && (r = $(this).attr("href") + "?Action=" + u, r += "&Others=" + $(this).data("others"), i = $("#" + $(this).data("form") + " :input").serializeObject(), i.fsOrdine = $("#sOrdine").val(), $(this).data("others").startsWith("MAIL") && (i.mailSign = Base64.encode(i.mailSign)), f = JSON.stringify(i), $(this).data("others").endsWith("true") ? SweetMessage.confirm("Queste impostazioni verranno salvate su tutti i plessi" + ($(this).data("others").startsWith("MAIL") ? "" : " dello stesso ordine") + ".<br>Confermi?", function () {
                    t(r, f)
                }) : t(r, f))
            });
            $("#chkSmtp").is(":checked") ? $("input[id^=smtp]").attr("disabled", !1) : ($("input[id^=smtp]").attr("disabled", !0), $("#btnSmtpTest").attr("disabled", !0));
            $("#chkSmtp").click(function () {
                $("input[id^=smtp]").attr("disabled", !this.checked);
                $("#btnSmtpSave").attr("disabled", this.checked);
                $("#btnSmtpTest").attr("disabled", !this.checked)
            });
            $("#smtpSSL").click(function () {
                $("#smtpPorta").val(this.checked ? "465" : "25")
            });
            $("input[id^=smtp]").change(function () {
                $("#btnSmtpSave").attr("disabled", !0);
                $("#btnSmtpTest").attr("disabled", !1)
            });
            $(".pannello-dirigente-impostazioni-email").unbind("click").click(function (n) {
                var t, i, r, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).data("others"), r = $("#" + $(this).data("form") + " :input").serializeObject(), u = JSON.stringify(r), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: i,
                    data: u,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (SweetMessage.success("Email inviata correttamente. E' possibile salvare le impostazioni."), $("#btnSmtpSave").attr("disabled", !1))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("input:checkbox[id^=chkEvento]").click(function () {
                var n = {}, t;
                n.eventoId = $(this).val();
                n.checked = $(this).is(":checked");
                t = JSON.stringify(n);
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=PANNELLO_DIRIGENTE_IMPOSTAZIONI_SALVA&Others=EVENTI|false",
                    data: t,
                    dataType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" && SweetMessage.error(n.errormsg)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            })
        }, initCalendario: function () {
            $("#btnNewPeriodo").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, Ajx.getOpenModal(i, !0))
            });
            $(".pannello-dirigente-calendario-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !0))
            });
            $(".pannello-dirigente-calendario-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), r = $(this).attr("href"), u = $(this).closest("tr").data("others");
                SweetMessage.confirm("Vuoi cancellare il periodo dal calendario?", function () {
                    var f, n, e;
                    t != undefined && (f = r + "?Action=" + t, n = {}, n.fsOthers = u, e = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: f,
                        data: e,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (i(), Notific8.success("Periodo eliminato correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                })
            })
        }, initCalendarioPeriodoModal: function () {
            handleInputField.init(!0, !1, !1, !1, !0);
            $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                $("#fdIni").focus()
            });
            $("#fdIni_dtp").on("dp.change", function (n) {
                $("#fdEnd_dtp").data("DateTimePicker").minDate(n.date)
            });
            $("#fdEnd_dtp").on("dp.change", function (n) {
                $("#fdIni_dtp").data("DateTimePicker").maxDate(n.date)
            });
            $(".pannello-dirigente-calendario-salva").unbind("click").click(function (n) {
                var t, r;
                if ((n.preventDefault(), t = $("#form-periodo"), t.length == 0 || t.validate().form()) && (r = $(this).data("action"), r != undefined)) {
                    var u = $(this).attr("href") + "?Action=" + r, f = t.serializeObject(), e = JSON.stringify(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                i();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initVoti: function () {
            $("#tableVoti").DataTable({paging: !1, searching: !1, ordering: !1, info: !1, fixedHeader: !0});
            $("input:checkbox[id^=chkUso]").click(function () {
                var n = {}, t;
                n.votoId = $(this).closest("tr").data("id");
                n.flag = $(this).attr("id").substr(6, 3).toLowerCase();
                n.checked = $(this).is(":checked");
                t = JSON.stringify(n);
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=PANNELLO_DIRIGENTE_VOTI_SALVA",
                    data: t,
                    dataType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" && SweetMessage.error(n.errormsg)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            });
            $(".pannello-dirigente-voti-copia,.pannello-dirigente-voti-disattiva").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), u = $(this).attr("href"), i = "";
                i = t === "PANNELLO_DIRIGENTE_VOTI_DISATTIVA" ? "<b>DISATTIVAZIONE VOTI<\/b><br>Con la seguente funzione verranno disattivati TUTTI i voti che presentano tutte le spunte attive.<br>Confermi?" : "E' stata richiesta la copia degli indicatori di utilizzo dei voti in tutti i plessi con lo stesso ordine scuola.<br>Confermi?";
                SweetMessage.confirm(i, function () {
                    if (t != undefined) {
                        var n = u + "?Action=" + t;
                        ApplyWait();
                        $.ajax({
                            type: "POST", cache: !1, url: n, async: !0, datatype: "json", success: function (n) {
                                HideWait();
                                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (Notific8.success("Operazione eseguita correttamente."), t === "PANNELLO_DIRIGENTE_VOTI_DISATTIVA" && r())
                            }, error: function (n, t, i) {
                                HideWait();
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            })
        }
    }
}(), Oggi = function () {
    function i(n) {
        var t = "../Secret/APP_Ajax_Get.aspx?Action=OGGI", i;
        t += "&Others=" + n;
        i = $("#ContentPlaceHolderBody_divContent");
        ApplyWait();
        $.ajax({
            type: "GET", cache: !1, url: t, async: !0, datatype: "html", success: function (n) {
                HideWait();
                i.html(n)
            }, error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    var n, t;
    return {
        init: function (r, u, f) {
            n = moment(u, "DD/MM/YYYY");
            t = moment(f, "DD/MM/YYYY");
            $("#fdData").datetimepicker({
                format: "dddd, D MMMM YYYY",
                locale: "it",
                ignoreReadonly: !0,
                showTodayButton: !0,
                calendarWeeks: !0,
                defaultDate: moment(r, "DD/MM/YYYY"),
                minDate: n,
                maxDate: t
            });
            $("#fdData").on("dp.change", function (n) {
                i(moment(n.date).format("DD/MM/YYYY"))
            });
            $(".oggi-naviga").unbind("click").click(function (r) {
                var f, u;
                r.preventDefault();
                f = $(this).data("others");
                f !== undefined && (u = $("#fdData").data("DateTimePicker").viewDate().add(f, "days"), u.isBefore(n) ? SweetMessage.error("Hai raggiunto l'inzio dell'anno scolastico.") : u.isAfter(t) ? SweetMessage.error("Hai raggiunto la fine dell'anno scolastico.") : i(u.format("DD/MM/YYYY")))
            });
            $(".oggi-firma-inserimento").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fdDate").val() + "|" + $(this).data("others"), Ajx.getOpenModal(i, !1))
            })
        }
    }
}(), Appello = function () {
    function t(t) {
        n(t, !1);
        i(t, !1);
        r(t, !1);
        u(t, !1);
        $("[id='MotivAssenza#R" + t + "#D01']").val("");
        f(!0)
    }

    function n(n, t, i) {
        t ? ($("[id='AssenzaAlunno#R" + n + "#D01']").attr("src", "../images/Appello/Appello_assenza.png"), $("[id='HIDAssenzaAlunno#R" + n + "#D01']").val(i ? "2" : "1"), i ? $("[id='AssenzaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_assenza_G.png") : $("[id='AssenzaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png")) : ($("[id='AssenzaAlunno#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"), $("[id='HIDAssenzaAlunno#R" + n + "#D01']").val("0"), $("[id='AssenzaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"))
    }

    function i(n, t, i, r, u) {
        t ? ($("[id='RitardoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_ritardo.png"), $("[id='HIDRitardoSiNo#R" + n + "#D01']").val(i ? "2" : "1"), $("[id='RitardoOra#R" + n + "#D01']").attr("disabled", !1), $("[id='RitardoOra#R" + n + "#D01']").val(r), $("[id='RitardoOrario#R" + n + "#D01']").attr("disabled", !1), $("[id='RitardoOrario#R" + n + "#D01']").val(u), i ? $("[id='RitardoGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_ritardo_G.png") : $("[id='RitardoGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png")) : ($("[id='RitardoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"), $("[id='HIDRitardoSiNo#R" + n + "#D01']").val("0"), $("[id='RitardoOra#R" + n + "#D01']").attr("disabled", !0), $("[id='RitardoOra#R" + n + "#D01']").val("0"), $("[id='RitardoOrario#R" + n + "#D01']").attr("disabled", !0), $("[id='RitardoOrario#R" + n + "#D01']").val("00:00"), $("[id='RitardoGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"))
    }

    function r(n, t, i, r, u) {
        t ? ($("[id='UscitaSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_uscita.png"), $("[id='HIDUscitaSiNo#R" + n + "#D01']").val(i ? "2" : "1"), $("[id='UscitaOra#R" + n + "#D01']").attr("disabled", !1), $("[id='UscitaOra#R" + n + "#D01']").val(r), $("[id='UscitaOrario#R" + n + "#D01']").attr("disabled", !1), $("[id='UscitaOrario#R" + n + "#D01']").val(u), i ? $("[id='UscitaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_uscita_G.png") : $("[id='UscitaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png")) : ($("[id='UscitaSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"), $("[id='HIDUscitaSiNo#R" + n + "#D01']").val("0"), $("[id='UscitaOra#R" + n + "#D01']").attr("disabled", !0), $("[id='UscitaOra#R" + n + "#D01']").val("0"), $("[id='UscitaOrario#R" + n + "#D01']").attr("disabled", !0), $("[id='UscitaOrario#R" + n + "#D01']").val("00:00"), $("[id='UscitaGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"))
    }

    function u(n, t, i, r, u) {
        t ? ($("[id='RientroSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_rientro.png"), $("[id='HIDRientroSiNo#R" + n + "#D01']").val(i ? "2" : "1"), $("[id='RientroOra#R" + n + "#D01']").attr("disabled", !1), $("[id='RientroOra#R" + n + "#D01']").val(r), $("[id='RientroOrario#R" + n + "#D01']").attr("disabled", !1), $("[id='RientroOrario#R" + n + "#D01']").val(u), i ? $("[id='RientroGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello_rientro_G.png") : $("[id='RientroGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png")) : ($("[id='RientroSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"), $("[id='HIDRientroSiNo#R" + n + "#D01']").val("0"), $("[id='RientroOra#R" + n + "#D01']").attr("disabled", !0), $("[id='RientroOra#R" + n + "#D01']").val("0"), $("[id='RientroOrario#R" + n + "#D01']").val("0"), $("[id='RientroOrario#R" + n + "#D01']").val("00:00"), $("[id='RientroGiustificatoSiNo#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"))
    }

    function f(n, t) {
        t ? ($("[id='Concorre#R" + n + "#D01']").attr("src", "../images/Appello/Appello_concorre.png"), $("[id='HIDConcorre#R" + n + "#D01']").val("1")) : ($("[id='Concorre#R" + n + "#D01']").attr("src", "../images/Appello/Appello.png"), $("[id='HIDConcorre#R" + n + "#D01']").val("0"))
    }

    function e(n, t) {
        $("[id='MotivAssenza#R" + n + "#D01']").val(t)
    }

    return {
        init: function () {
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=chk]").not(this).prop("checked", this.checked)
            });
            $("#btnEvento").unbind("click").click(function (o) {
                o.preventDefault();
                $("td input:checkbox[id^=chk]:checked").length > 0 ? (handleInputField.init(!1, !0, !1, !0, !1), $("#eventoModal").modal({backdrop: "static"}), $("input[type=radio]").change(function () {
                    switch ($(this).val()) {
                        case"P":
                            $("#fsOraLez").attr("disabled", !0);
                            $("#ftOrario").attr("disabled", !0);
                            $("#fsNote").attr("disabled", !0);
                            $("#fbGiust").bootstrapSwitch("disabled", !0);
                            $("#fbCalc").bootstrapSwitch("disabled", !0);
                            break;
                        case"A":
                            $("#fsOraLez").attr("disabled", !0);
                            $("#ftOrario").attr("disabled", !0);
                            $("#fsNote").attr("disabled", !1);
                            $("#fbGiust").bootstrapSwitch("disabled", !1);
                            $("#fbCalc").bootstrapSwitch("disabled", !1);
                            break;
                        case"E":
                        case"U":
                        case"R":
                            $("#fsOraLez").attr("disabled", !1);
                            $("#ftOrario").attr("disabled", !1);
                            $("#ftOrario").val(moment().format("HH:mm"));
                            $("#fsNote").attr("disabled", !1);
                            $("#fbGiust").bootstrapSwitch("disabled", !1);
                            $("#fbCalc").bootstrapSwitch("disabled", !1)
                    }
                }), $("#btnApplica").unbind("click").click(function (o) {
                    o.preventDefault();
                    var l = $("#fbEvento:checked").val(), s = $("#fsOraLez").val(), h = $("#ftOrario").val(),
                        v = $("#fsNote").val(), a = $("#fbGiust").is(":checked"), c = $("#fbCalc").is(":checked");
                    $("#eventoModal").modal("hide");
                    $("td input:checkbox[id^=chk]:checked").each(function () {
                        var o = $(this).attr("id").slice(-2);
                        switch (l) {
                            case"P":
                                t(o);
                                break;
                            case"A":
                                t(o);
                                n(o, !0, a);
                                break;
                            case"E":
                                n(o, !1, !1);
                                i(o, !0, a, s, h);
                                break;
                            case"U":
                                n(o, !1, !1);
                                r(o, !0, c, s, h);
                                break;
                            case"R":
                                n(o, !1, !1);
                                u(o, !0, c, s, h)
                        }
                        l !== "P" && (f(o, c), e(o, v))
                    });
                    REOnChange()
                })) : SweetMessage.error("Nessun alunno selezionato<br>E' necessario selezionare almeno un alunno.")
            })
        }
    }
}(), AlunnoScheda = function () {
    function n(n, t) {
        $.ajax({
            type: "POST", cache: !1, url: n, data: t, dataType: "json", async: !0, success: function (n) {
                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (HideWait(), Notific8.success("Operazione eseguita correttamente."), $(".fileinput-save").addClass("hidden"))
            }, error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    function t(n, t) {
        var i = $($(n.target).attr("href")), r, u;
        i.html() === "" && (r = i.data("action"), r !== undefined && (u = i.attr("href") + "?Action=" + r, u += "&Others=" + $("#fsFunz").val() + "|" + $("#fsGuids").val() + "|" + $("#fsFlags").val(), ApplyWait(), $.ajax({
            type: "GET", cache: !1, url: u, async: !0, datatype: "html", success: function (n) {
                HideWait();
                i.html(n);
                switch (r) {
                    case"ALUNNO_SCHEDA_ASSENZE":
                        $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"});
                        $("#btnGriglia").on("click", function () {
                            $("#tableElenco").hide();
                            $("#tableGriglia").show()
                        });
                        $("#btnElenco").on("click", function () {
                            $("#tableGriglia").hide();
                            $("#tableElenco").show()
                        });
                        t && $("#asseTab").DataTable({
                            scrollY: "405px",
                            scrollCollapse: !1,
                            paging: !1,
                            searching: !1,
                            ordering: !1,
                            info: !1
                        });
                        break;
                    case"ALUNNO_SCHEDA_NOTE_DISCIPLINARI":
                        t && $("#noteTab").DataTable({
                            scrollY: "440px",
                            scrollCollapse: !1,
                            paging: !1,
                            searching: !1,
                            ordering: !1,
                            info: !1
                        });
                        break;
                    case"ALUNNO_SCHEDA_NOTE_DOCENTE_SELEZIONE":
                        $(".alunno-scheda-note-docente-visualizza").unbind("click").click(function (n) {
                            var t, i;
                            n.preventDefault();
                            t = $(this).data("action");
                            t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).data("others"), Ajx.getOpenModal(i, !1))
                        })
                }
            }, error: function (n, t, i) {
                HideWait();
                SweetMessage.error(i.toString())
            }
        })))
    }

    function i() {
        $("#scheda_note").val(Base64.decode($("#scheda_note").val()));
        $("#scheda_note").summernote({
            height: 250,
            lang: "it-IT",
            toolbar: [["mybutton", ["save"]], ["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]],
            buttons: {save: r}
        });
        $("#fsFlags").val().charAt(5) !== "1" && $("#scheda_note").summernote("disable");
        $(".fileinput").on("change.bs.fileinput", function () {
            $(".fileinput-save").removeClass("hidden")
        });
        $(".fileinput").on("clear.bs.fileinput", function () {
            $(".fileinput-save").removeClass("hidden")
        });
        $(".fileinput-save").unbind("click").click(function (t) {
            var u, r, e;
            if (t.preventDefault(), u = $(this).data("action"), u != undefined) {
                var o = $(this).attr("href") + "?Action=" + u, i = "", f = $("#FileToUpload")[0];
                if (f !== undefined && f.files.length > 0) {
                    if (r = f.files[0], r.size > 2097152) {
                        SweetMessage.error("Non Ã¨ possibile selezionare foto piÃ¹ grandi di 2Mb. Selezionare una foto piÃ¹ piccola.");
                        return
                    }
                    ApplyWait();
                    e = new FormData;
                    e.append(r.name, r);
                    $.ajax({
                        url: "../../Handlers/SD_UploadHandler.ashx?UseGuid=true&Type=FO&Others=" + $(this).data("others"),
                        type: "POST",
                        data: e,
                        contentType: !1,
                        processData: !1,
                        success: function (t) {
                            i = '{ "fsGuids": "' + $("#fsGuids").val() + '",   "fsFoto": "' + t + '"}';
                            n(o, i)
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                } else i = '{ "fsGuids": "' + $("#fsGuids").val() + '",   "fsFoto": "DELETE"}', n(o, i)
            }
        })
    }

    var r = function () {
        var n = $.summernote.ui, t = n.button({
            className: "btn btn-primary",
            contents: '<i class="far fa-save"/> Salva',
            tooltip: "Salva le note",
            click: function () {
                var n;
                if ($("#scheda_note").val().length > 131072) {
                    SweetMessage.error("E' stata superata la dimensione massima del campo testo. Ridurre la quantitÃ  del testo inserito. Verificare.");
                    return
                }
                n = '{ "fsGuids": "' + $("#fsGuids").val() + '",   "fuiNota": "' + $("#fuiNota").val() + '",   "fsTesto": "' + Base64.encode($("#scheda_note").val()) + '"}';
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=ALUNNO_SCHEDA_NOTE_SALVA",
                    data: n,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (Notific8.success("Note salvate."), $("#fuiNota").val(n.json))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            }
        });
        return t.render()
    }, u = function () {
        var n = $.summernote.ui, t = n.button({
            className: "btn btn-primary",
            contents: '<i class="far fa-save"/> Salva',
            tooltip: "Salva le note del docente",
            click: function () {
                var n;
                if ($("#note_docente").val().length > 131072) {
                    SweetMessage.error("E' stata superata la dimensione massima del campo testo. Ridurre la quantitÃ  del testo inserito. Verificare.");
                    return
                }
                n = '{ "fsGuids": "' + $("#fsNotaGuids").val() + '",   "fsTesto": "' + Base64.encode($("#note_docente").val()) + '"}';
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=ALUNNO_SCHEDA_NOTE_DOCENTE_SALVA",
                    data: n,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (Notific8.success("Note salvate."), $("#fsNotaGuids").val(n.json))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            }
        });
        return t.render()
    };
    return {
        init: function () {
            $(".alunno-scheda").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).data("others"), Ajx.getOpenModal(i, !1))
            })
        }, initModal: function () {
            i();
            $("#modal-stack-" + iStackModal).on("shown.bs.tab", function (n) {
                t(n, !0)
            })
        }, initNoteModal: function () {
            var n = $("#modal-stack-" + iStackModal);
            n.css({"margin-top": "45px"});
            $("#note_docente").val(Base64.decode($("#note_docente").val()));
            $("#note_docente").summernote({
                height: 380,
                lang: "it-IT",
                toolbar: [["mybutton", ["save"]], ["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]],
                buttons: {save: u}
            });
            $("#fbEdit").val() !== "1" && $("#note_docente").summernote("disable")
        }, initAlunnoRicerca: function () {
            $("#fuiAlunno").select2("destroy");
            $("#fuiAlunno").select2({
                placeholder: "Seleziona un alunno ...",
                minimumInputLength: 3,
                ajax: {
                    url: "../Secret/APP_Ajax_Get.aspx?Action=SEEKER_ALUNNO_UTENTE",
                    dataType: "json",
                    quietMillis: 250,
                    data: function (n) {
                        return {q: n}
                    },
                    results: function (n) {
                        return {results: n}
                    },
                    cache: !1
                }
            });
            $("#fuiAlunno").select2("focus");
            $("#fuiAlunno").on("change", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + n.val + "|" + n.added.classe + "|" + n.added.plesso, ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        $("#content-alunno").html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            })
        }, initAlunnoRicercaChange: function () {
            i();
            $("#content-alunno").unbind("shown.bs.tab").on("shown.bs.tab", function (n) {
                t(n, !1)
            })
        }, initAlunnoRicercaCurriculum: function () {
            $(".alunno-ricerca-curriculum-asl").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#fuiAlunno").select2("val") + "|" + $(this).data("others")), Ajx.getOpenModal(i, !1))
            })
        }, initAlunnoRicercaVoti: function () {
            $("#ddlFrazione,#ddlMateria").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#fsFunz").val() + "|" + $("#fsGuids").val() + "|" + $("#ddlFrazione").val() + "|" + $("#ddlMateria").val()), $("#content-voti").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        $("#content-voti").html(n)
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#content-voti").html("Errore imprevisto.")
                    }
                }))
            });
            $("#ddlFrazione").trigger("change")
        }, initAlunnoRicercaVotiView: function () {
            $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
        }
    }
}(), Annotazioni = function () {
    var n = function () {
        var n = $.summernote.ui, t = n.button({
            className: "btn btn-primary",
            contents: '<i class="far fa-save"/> Salva',
            tooltip: "Salva le annotazioni",
            click: function () {
                var n = '{ "fsNotaOthers": "' + $("#fsNotaOthers").val() + '",   "fsTesto": "' + Base64.encode($("#note_docente").val()) + '"}';
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=NOTE_DOCENTE_SALVA",
                    data: n,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (Notific8.success("Note salvate."), $("#fsNotaOthers").val(n.json))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            }
        });
        return t.render()
    };
    return {
        initModal: function () {
            $("#note_docente").val(Base64.decode($("#note_docente").val()));
            $("#note_docente").summernote({
                height: 420,
                lang: "it-IT",
                toolbar: [["mybutton", ["save"]], ["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]],
                buttons: {save: n}
            });
            $("#fbEdit").val() !== "1" && $("#note_docente").summernote("disable")
        }
    }
}(), RegistroClasse = function () {
    function n(n) {
        var t = $(n), i, r;
        t.html() === "" && (i = t.data("action"), i !== undefined && (r = t.attr("href") + "?Action=" + i, r += "&Others=" + $("#fsGuids").val() + "|" + $("#ContentPlaceHolderBody_idAluSelected").val(), ApplyWait(), $.ajax({
            type: "GET",
            cache: !1,
            url: r,
            async: !0,
            datatype: "html",
            success: function (n) {
                HideWait();
                t.html(n)
            },
            error: function (i, r, u) {
                HideWait();
                n === "#rcla-tab-alunno" ? t.html("<div class='alert alert-danger'>" + u.toString() + "<\/div>") : SweetMessage.error(u.toString())
            }
        })))
    }

    var t, i;
    return {
        init: function (r, u, f) {
            t = moment(u, "DD/MM/YYYY");
            i = moment(f, "DD/MM/YYYY");
            AlunnoScheda.init();
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=chkAlu]").not(this).prop("checked", this.checked)
            });
            $("#tabAlu .cognome").unbind("click").click(function (t) {
                t.preventDefault();
                $(this).closest("tr").hasClass("primary") ? ($(this).closest("tr").removeClass("primary"), $("#ContentPlaceHolderBody_idAluSelected").val("nothing"), $("#rcla-tab-alunno").html(""), $('[data-toggle="tab"][href="#rcla-tab-note"]').tab("show")) : ($(this).closest("tr").addClass("primary").siblings().removeClass("primary"), $("#ContentPlaceHolderBody_idAluSelected").val($(this).closest("tr").data("aluid")), $("#rcla-tab-alunno").html(""), $('[data-toggle="tab"][href="#rcla-tab-alunno"]').tab("show"), n("#rcla-tab-alunno"))
            });
            $("input[name^=chkAssenza]").unbind("change").change(function () {
                var t = this, i;
                sendObj = {};
                sendObj.fsGuids = $("#fsGuids").val();
                sendObj.flgAss = $(this).is(":checked");
                sendObj.aluId = $(this).closest("tr").data("aluid");
                i = JSON.stringify(sendObj);
                ApplyWait();
                $.ajax({
                    type: "POST",
                    cache: !1,
                    url: "../Secret/APP_Ajax_Post.aspx?Action=REGISTRO_CLASSE_ASSENZA_SALVA",
                    data: i,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (i) {
                        HideWait();
                        i.errorcode !== "0" ? SweetMessage.error(i.errormsg) : ($(t).closest("td").next("td").removeClass().addClass("text-center"), $(t).is(":checked") && $(t).closest("td").next("td").addClass("bg-status-1000"), $("#ContentPlaceHolderBody_idAluSelected").val() === $(t).closest("tr").data("aluid") && ($("#rcla-tab-alunno").html(""), n("#rcla-tab-alunno")))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })
            });
            $("#rcla-content-right").on("shown.bs.tab", function (t) {
                t.preventDefault();
                n($(t.target).attr("href"))
            });
            $("#btnEvento").unbind("click").click(function (n) {
                n.preventDefault();
                $("td input:checkbox[id^=chkAlu]:checked").length > 0 ? (handleInputField.init(!1, !0, !1, !0, !1), $("#eventoModal").modal({backdrop: "static"}), $("input[type=radio]").change(function () {
                    switch ($(this).val()) {
                        case"P":
                            $("#fsOraLez").attr("disabled", !0);
                            $("#ftOrario").attr("disabled", !0);
                            $("#fsNote").attr("disabled", !0);
                            $("#fbGiust").bootstrapSwitch("disabled", !0);
                            $("#fbCalc").bootstrapSwitch("disabled", !0);
                            break;
                        case"A":
                            $("#fsOraLez").attr("disabled", !0);
                            $("#ftOrario").attr("disabled", !0);
                            $("#fsNote").attr("disabled", !1);
                            $("#fbGiust").bootstrapSwitch("disabled", !1);
                            $("#fbCalc").bootstrapSwitch("disabled", !1);
                            break;
                        case"E":
                        case"U":
                        case"R":
                            $("#fsOraLez").attr("disabled", !1);
                            $("#ftOrario").attr("disabled", !1);
                            $("#ftOrario").val(moment().format("HH:mm"));
                            $("#fsNote").attr("disabled", !1);
                            $("#fbGiust").bootstrapSwitch("disabled", !1);
                            $("#fbCalc").bootstrapSwitch("disabled", !1)
                    }
                }), $("#btnApplica").unbind("click").click(function (n) {
                    n.preventDefault();
                    var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                    SweetMessage.confirm("Confermi la registrazione dell'evento per gli alunnui selezionati?", function () {
                        var n, r;
                        $("#eventoModal").modal("hide");
                        t !== undefined && (n = {}, n.fsGuids = $("#fsGuids").val(), $("td input:checkbox[id^=chk]:checked").each(function () {
                            n[this.id] = $(this).val()
                        }), n.fsEvento = $("#fbEvento:checked").val(), n.sOraLez = $("#fsOraLez").val(), n.sOrario = $("#ftOrario").val(), n.sNote = $("#fsNote").val(), n.bIsGiust = $("#fbGiust").is(":checked"), n.bIsCalc = $("#fbCalc").is(":checked"), r = JSON.stringify(n), ApplyWait(), $.ajax({
                            type: "POST",
                            cache: !1,
                            url: i,
                            data: r,
                            dataType: "json",
                            contentType: "json",
                            async: !0,
                            success: function (n) {
                                HideWait();
                                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : Notific8.success("Operazione eseguita correttamente.")
                            },
                            error: function (n, t, i) {
                                HideWait();
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        }))
                    })
                })) : SweetMessage.error("Nessun alunno selezionato<br>E' necessario selezionare almeno un alunno.")
            })
        }, initComunicazioni: function () {
            DownloadFileConHandler.DownloadFile();
            $(".registro-classe-comunicazione-letta").unbind("click").click(function (n) {
                var u, t, f;
                n.preventDefault();
                var i = $(this).attr("data-action"), e = $(this), r = $(this).is(":checked");
                i !== undefined && (u = $(this).attr("href") + "?Action=" + i, t = {}, t.fsGuids = $("#fsGuids").val(), t.comunicazioneId = $(this).closest("tr").data("id"), t.checked = r, f = JSON.stringify(t), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: u,
                    data: f,
                    async: !1,
                    datatype: "json",
                    success: function () {
                        r && e.closest("td").html("<label class='mt-checkbox mt-checkbox-outline mt-checkbox-disabled'><input type='checkbox' checked disabled><span><\/span><\/label>")
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("#filtroComunicazioni").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $("#ContentPlaceHolderBody_idAluSelected").val() + "|" + $(this).val(), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !1,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        $("#rcla-tab-chat").html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            })
        }, initFirme: function () {
            $("#btnNewFirma").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val(), Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-firma-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-firma-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), r = $(this).attr("href") + "?Action=" + t, i = this;
                SweetMessage.confirm("Confermi la cancellazione della firma?", function () {
                    var n, u;
                    t !== undefined && (n = {}, n.firmaId = $(i).closest("tr").data("id"), u = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($(i).closest("tr").remove(), Notific8.success("Firma eliminata correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initFirmaModal: function () {
            $("#fuiAlunniIds").select2({placeholder: "Seleziona uno o piÃ¹ alunni...", allowClear: !0});
            $("#fsDocente").change(function () {
                $("#fsDocente").val() !== Base64.decode($("#fsGuids").val()).split("|")[0] ? ($("#fsUserId,#fsUserPwd").show(), $("#fsUserId,#fsUserPwd").val("")) : $("#fsUserId,#fsUserPwd").hide()
            });
            $(".registro-classe-firma-salva").unbind("click").click(function (t) {
                var i, r, f, u, e;
                if (t.preventDefault(), $("#firma-error").html(""), i = $("#form-firma"), i.length === 0 || i.validate().form()) {
                    if (parseInt($("#fiOra").val()) + parseInt($("#fiPerOre").val()) > parseInt($("#fiMaxOre").val()) + 1) {
                        $("#firma-error").html("<div class='alert alert-danger'>Non si possono firmare tutte queste ore.<\/div>");
                        return
                    }
                    if ($("#fsDocente").val() !== Base64.decode($("#fsGuids").val()).split("|")[0] && ($("#fsUserId").val() === "" || $("#fsUserPwd").val() === "")) {
                        $("#firma-error").html("<div class='alert alert-danger'>E' obbligatorio indicare l'utente e la password del docente che sta firmando.<\/div>");
                        return
                    }
                    r = $(this).data("action");
                    r !== undefined && (f = $(this).attr("href") + "?Action=" + r, u = i.serializeObject(), u.fsGuids = $("#fsGuids").val(), e = JSON.stringify(u), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: f,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (HideWait(), t.errorcode !== "0") $("#firma-error").html("<div class='alert alert-danger'>" + t.errormsg + "<\/div>"); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                $("#rcla-tab-firme").html("");
                                n("#rcla-tab-firme");
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            $("#firma-error").html("<div class='alert alert-danger'>Errore durante la chiamata al Server: " + i.toString() + "<\/div>")
                        }
                    }))
                }
            })
        }, initNote: function () {
            $(".panel-heading span.collapsible").unbind("click").click(function () {
                var n = $(this);
                n.hasClass("panel-collapsed") ? (n.parents(".panel").find(".table-responsive").slideDown(200), n.parents(".panel").find(".panel-body").slideDown(200), n.removeClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-down").addClass("fa fa-chevron-up")) : (n.parents(".panel").find(".table-responsive").slideUp(200), n.parents(".panel").find(".panel-body").slideUp(200), n.addClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-up").addClass("fa-chevron-down"))
            });
            $(".registro-classe-nota-inserimento").unbind("click").click(function (n) {
                var i, r, t, u;
                n.preventDefault();
                i = $(this).data("action");
                i !== undefined && (r = $(this).attr("href") + "?Action=" + i, t = {}, t.fsGuids = $("#fsGuids").val(), t.fsTipo = $(this).data("others"), $("td input:checkbox[id^=chk]:checked").each(function () {
                    t[this.id] = $(this).val()
                }), u = JSON.stringify(t), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    async: !0,
                    data: u,
                    datatype: "html",
                    success: function (n) {
                        handleModal.openModal(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $(".registro-classe-nota-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).closest("tr").data("id") + "|1", Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-nota-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), r = $(this).attr("href") + "?Action=" + t, i = this;
                SweetMessage.confirm("Confermi la cancellazione?", function () {
                    var n, u;
                    t !== undefined && (n = {}, n.notaId = $(i).closest("tr").data("id"), u = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($(i).closest("tr").remove(), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initNotaModal: function (t) {
            handleInputField.init(!0, !1, !1, !1, !0, !1);
            $(".registro-classe-nota-salva").unbind("click").click(function (i) {
                var u, f, e, r, o;
                (i.preventDefault(), u = $("#form-nota"), u.length === 0 || u.validate().form()) && (f = $(this).data("action"), f != undefined && (e = $(this).attr("href") + "?Action=" + f, r = u.serializeObject(), r.fsGuids = $("#fsGuids").val(), t === 1 ? $("td input:checkbox[id^=chk]:checked").each(function () {
                    r[this.id] = $(this).val()
                }) : r.chkAlu = $("#ContentPlaceHolderBody_idAluSelected").val(), o = JSON.stringify(r), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: e,
                    data: o,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (i) {
                        if (HideWait(), i.errorcode !== "0") SweetMessage.error(i.errormsg); else {
                            var r = $("#modal-stack-" + iStackModal);
                            r.modal("hide");
                            $("#rcla-tab-note").html("");
                            t === 1 ? n("#rcla-tab-note") : ($("#rcla-tab-alunno").html(""), n("#rcla-tab-alunno"));
                            Notific8.success("Operazione eseguita correttamente.")
                        }
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            })
        }, initAlunno: function () {
            handleInputField.init(!1, !0, !1, !1, !1, !1);
            $(".panel-heading span.collapsible").unbind("click").click(function () {
                var n = $(this);
                n.hasClass("panel-collapsed") ? (n.parents(".panel").find(".table-responsive").slideDown(200), n.parents(".panel").find(".panel-body").slideDown(200), n.parents(".panel").find(".panel-footer").slideDown(200), n.removeClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-down").addClass("fa fa-chevron-up")) : (n.parents(".panel").find(".table-responsive").slideUp(200), n.parents(".panel").find(".panel-body").slideUp(200), n.parents(".panel").find(".panel-footer").slideUp(200), n.addClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-up").addClass("fa-chevron-down"))
            });
            $("#chkAluAss").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#chkAluAssGius").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluAssGius").prop("disabled", !1), $("#chkAluRit").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRit").prop("disabled", !1), $("#chkAluRit").prop("checked", !1), $("#ftAluRitOra").val("00:00"), $("#ftAluRitOra").prop("disabled", !0), $("#fsAluRitOra").val("0"), $("#fsAluRitOra").prop("disabled", !0), $("#chkAluRitGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRitGius").prop("disabled", !0), $("#chkAluRitGius").prop("checked", !1), $("#chkAluUsc").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluUsc").prop("disabled", !1), $("#chkAluUsc").prop("checked", !1), $("#ftAluUscOra").val("00:00"), $("#ftAluUscOra").prop("disabled", !0), $("#fsAluUscOra").val("0"), $("#fsAluUscOra").prop("disabled", !0), $("#chkAluUscGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluUscGius").prop("disabled", !0), $("#chkAluUscGius").prop("checked", !1), $("#chkAluRie").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRie").prop("disabled", !1), $("#chkAluRie").prop("checked", !1), $("#ftAluRieOra").val("00:00"), $("#ftAluRieOra").prop("disabled", !0), $("#fsAluRieOra").val("0"), $("#fsAluRieOra").prop("disabled", !0), $("#chkAluRieGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRieGius").prop("disabled", !0), $("#chkAluRieGius").prop("checked", !1), $("#chkAluMensa").prop("checked", !1)) : ($("#chkAluAssGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluAssGius").prop("disabled", !0), $("#chkAluAssGius").prop("checked", !1), $("#chkAluMensa").prop("checked", !0))
            });
            $("#chkAluAssGius").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#chkAluAss").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluAss").prop("disabled", !0)) : ($("#chkAluAss").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluAss").prop("disabled", !1))
            });
            $("#chkAluRit").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#ftAluRitOra").val(moment().format("HH:mm")), $("#ftAluRitOra").prop("disabled", !1), $("#fsAluRitOra").prop("disabled", !1), $("#chkAluRitGius").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRitGius").prop("disabled", !1), $("#chkAluAss").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluAss").prop("disabled", !1), $("#chkAluAss").prop("checked", !1), $("#chkAluAssGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluAssGius").prop("disabled", !0), $("#chkAluAssGius").prop("checked", !1)) : ($("#ftAluRitOra").val("00:00"), $("#ftAluRitOra").prop("disabled", !0), $("#fsAluRitOra").val("0"), $("#fsAluRitOra").prop("disabled", !0), $("#chkAluRitGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRitGius").prop("disabled", !0), $("#chkAluRitGius").prop("checked", !1))
            });
            $("#chkAluRitGius").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#chkAluRit").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRit").prop("disabled", !0), $("#ftAluRitOra").prop("disabled", !0), $("#fsAluRitOra").prop("disabled", !0)) : ($("#chkAluRit").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRit").prop("disabled", !1), $("#ftAluRitOra").prop("disabled", !1), $("#fsAluRitOra").prop("disabled", !1))
            });
            $("#chkAluUsc").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#ftAluUscOra").val(moment().format("HH:mm")), $("#ftAluUscOra").prop("disabled", !1), $("#fsAluUscOra").prop("disabled", !1), $("#chkAluUscGius").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluUscGius").prop("disabled", !1), $("#chkAluAss").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluAss").prop("disabled", !1), $("#chkAluAss").prop("checked", !1), $("#chkAluAssGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluAssGius").prop("disabled", !0), $("#chkAluAssGius").prop("checked", !1)) : ($("#ftAluUscOra").val("00:00"), $("#ftAluUscOra").prop("disabled", !0), $("#fsAluUscOra").val("0"), $("#fsAluUscOra").prop("disabled", !0), $("#chkAluUscGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluUscGius").prop("disabled", !0), $("#chkAluUscGius").prop("checked", !1))
            });
            $("#chkAluUscGius").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#chkAluUsc").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluUsc").prop("disabled", !0), $("#ftAluUscOra").prop("disabled", !0), $("#fsAluUscOra").prop("disabled", !0)) : ($("#chkAluUsc").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluUsc").prop("disabled", !1), $("#ftAluUscOra").prop("disabled", !1), $("#fsAluUscOra").prop("disabled", !1))
            });
            $("#chkAluRie").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#ftAluRieOra").val(moment().format("HH:mm")), $("#ftAluRieOra").prop("disabled", !1), $("#fsAluRieOra").prop("disabled", !1), $("#chkAluRieGius").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRieGius").prop("disabled", !1), $("#chkAluAss").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluAss").prop("disabled", !1), $("#chkAluAss").prop("checked", !1), $("#chkAluAssGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluAssGius").prop("disabled", !0), $("#chkAluAssGius").prop("checked", !1)) : ($("#ftAluRieOra").val("00:00"), $("#ftAluRieOra").prop("disabled", !0), $("#fsAluRieOra").val("0"), $("#fsAluRieOra").prop("disabled", !0), $("#chkAluRieGius").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRieGius").prop("disabled", !0), $("#chkAluRieGius").prop("checked", !1))
            });
            $("#chkAluRieGius").unbind("click").click(function () {
                $(this).is(":checked") ? ($("#chkAluRie").closest("label").addClass("mt-checkbox-disabled"), $("#chkAluRie").prop("disabled", !0), $("#ftAluRieOra").prop("disabled", !0), $("#fsAluRieOra").prop("disabled", !0)) : ($("#chkAluRie").closest("label").removeClass("mt-checkbox-disabled"), $("#chkAluRie").prop("disabled", !1), $("#ftAluRieOra").prop("disabled", !1), $("#fsAluRieOra").prop("disabled", !1))
            });
            $(".registro-classe-alunno-giustifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $("#alunnoId").val(), Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-alunno-assenza-salva").unbind("click").click(function (n) {
                var i, r, t, u;
                n.preventDefault();
                i = $(this).data("action");
                i != undefined && (r = $(this).attr("href") + "?Action=" + i, t = {}, t.fsGuids = $("#fsGuids").val(), t.alunnoId = $("#alunnoId").val(), $("#rcla-tab-alunno td input:checkbox[id^=chkAlu]:checked").each(function () {
                    t[this.id] = $(this).val()
                }), $("#rcla-tab-alunno td input:not([id^=chkAlu])").each(function () {
                    t[this.id] = $(this).val()
                }), $("#rcla-tab-alunno td select").each(function () {
                    t[this.id] = $(this).val()
                }), u = JSON.stringify(t), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    data: u,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : Notific8.success("Operazione eseguita correttamente.")
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $(".registro-classe-alunno-lezione-inserimento").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $("#alunnoId").val(), Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-alunno-lezione-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-alunno-lezione-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = this, i = $(this).data("action"), r = $(this).attr("href") + "?Action=" + i;
                SweetMessage.confirm("Confermi la cancellazione della lezione individuale?", function () {
                    var n, u;
                    i !== undefined && (n = {}, n.lezioneId = $(t).closest("tr").data("id"), u = JSON.stringify(n), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($(t).closest("tr").remove(), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            });
            $(".registro-classe-alunno-nota-inserimento").unbind("click").click(function (n) {
                var i, r, t, u;
                n.preventDefault();
                i = $(this).data("action");
                i !== undefined && (r = $(this).attr("href") + "?Action=" + i, t = {}, t.fsGuids = $("#fsGuids").val(), t.fsTipo = $(this).data("others"), t.chkAlu = $("#ContentPlaceHolderBody_idAluSelected").val(), u = JSON.stringify(t), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    async: !0,
                    data: u,
                    datatype: "html",
                    success: function (n) {
                        handleModal.openModal(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $(".registro-classe-alunno-nota-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).closest("tr").data("id") + "|0", Ajx.getOpenModal(i, !1))
            });
            $(".registro-classe-alunno-nota-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), r = $(this).attr("href") + "?Action=" + t, i = this;
                SweetMessage.confirm("Confermi la cancellazione?", function () {
                    var n, u;
                    t !== undefined && (n = {}, n.notaId = $(i).closest("tr").data("id"), u = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($(i).closest("tr").remove(), $("#rcla-tab-note").html(""), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initGiustificaModal: function () {
            $("#form-giustifica").on("input", function (n) {
                $(n.target).closest("tr").attr("changed", "true")
            });
            $(".registro-classe-alunno-giustifica-salva").unbind("click").click(function (n) {
                var i, r, t, u;
                n.preventDefault();
                i = $(this).data("action");
                i != undefined && (r = $(this).attr("href") + "?Action=" + i, t = {}, t.fsGuids = $("#fsGuids").val(), t.alunnoId = $("#alunnoId").val(), $("#form-giustifica tr[changed='true']").each(function () {
                    var n = $(this).data("row");
                    t["row" + n] = $(this).data("id");
                    $("#chkAssGius" + n).length !== 0 && (t["chkAssGius" + n] = $("#chkAssGius" + n).is(":checked"));
                    $("#chkRitGius" + n).length !== 0 && (t["chkRitGius" + n] = $("#chkRitGius" + n).is(":checked"));
                    $("#chkUscGius" + n).length !== 0 && (t["chkUscGius" + n] = $("#chkUscGius" + n).is(":checked"));
                    $("#chkRieGius" + n).length !== 0 && (t["chkRieGius" + n] = $("#chkRieGius" + n).is(":checked"));
                    t["chkClc" + n] = $("#chkClc" + n).is(":checked");
                    t["fsAssMotivo" + n] = $("#fsAssMotivo" + n).val()
                }), u = JSON.stringify(t), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    data: u,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                            var t = $("#modal-stack-" + iStackModal);
                            t.modal("hide");
                            Notific8.success("Operazione eseguita correttamente.")
                        }
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            })
        }, updatePanel: function (t) {
            $(t).html("");
            n(t)
        }
    }
}(), QuadroRiepilogativo = function () {
    function r() {
        var t, r = $("#chkPunto").is(":checked"), i = $("#chkInvio").is(":checked"), n = "";
        $("input[name^=fbFrase]:checked").each(function () {
            var u = $(this).val();
            u !== "" && (r ? (t = "", CanAddsPunctuation(n) && Left(u, 1) === UCase(Left(u, 1)) && (t = "."), n !== "" && (n += t, EndsWithSpecialChar(n) || (n += " ")), i && EndsWithSpecialChar2(n) && (n += "\n"), n += u) : (n !== "" && (n += " ", i && EndsWithSpecialChar2(n) && (n += "\n")), n += u))
        });
        $("#fsCompGiud").val(n.trim())
    }

    var n, i, t;
    return {
        init: function () {
            $("#tabVoti > tbody > tr").unbind("click").click(function () {
                $(this).closest("tr").addClass("primary").siblings().removeClass("primary");
                $("#ContentPlaceHolderBody_idAluSelected").val($(this).closest("tr").data("aluidarc"))
            });
            QuadroRiepilogativo.initDettaglioVoti()
        }, initDettaglioVoti: function () {
            $(".alunno-dettaglio-quadro").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            })
        }, initVotiProposti: function (r) {
            $(".export-schede-carenze").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.fsGuids = $("#fsGuids").val(), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
            });
            $(".scheda-carenze").unbind("click").click(function (t) {
                var r, u;
                t.preventDefault();
                i = this;
                n = $(this).closest("tr").data("row");
                r = $(this).data("action");
                r != undefined && (u = $(this).attr("href") + "?Action=" + r, u += "&Others=" + $(this).closest("tr").data("others") + "|" + $(this).closest("tr").attr("data-id"), Ajx.getOpenModal(u, !1))
            });
            $(".voti-proposti-giudizio-materia").unbind("click").click(function (i) {
                var u, f;
                i.preventDefault();
                u = $(this);
                n = u.data("row");
                t = "#fsGiudizio";
                f = bootbox.dialog({
                    title: "Giudizio materia " + (r === "VP" ? "proposto " : "") + "di: " + $(this).closest("tr").data("nome"),
                    message: '<div class="bootstrap row"><div class="col-md-12"><div class="form-group"><textarea class="form-control" name="fsGiudizio" id="fsGiudizio" rows="6" maxlength="4096" ' + ($("#bLocked").val() === "1" || r === "RC" ? "readonly" : "") + ">" + $("#GiudizioFinale\\#R" + n).val() + "<\/textarea><\/div><\/div><\/div>",
                    closeButton: !0,
                    onEscape: !0,
                    buttons: {
                        confirm: {
                            label: "<i class='fa fa-check'><\/i> Salva",
                            className: "btn-primary " + ($("#bLocked").val() === "1" || r === "RC" ? "invisible" : ""),
                            callback: function () {
                                $("#GiudizioFinale\\#R" + n).val($("#fsGiudizio").val().trim());
                                $("#fsGiudizio").val().trim() === "" ? ($(u).removeClass("btn-success"), $(u).addClass("btn-warning")) : ($(u).removeClass("btn-warning"), $(u).addClass("btn-success"))
                            }
                        },
                        cancel: {label: "<i class='fas fa-times'><\/i> Chiudi", className: "btn-default"},
                        wizard: {
                            label: "<i class='fas fa-magic'><\/i> Componi",
                            className: "btn-info pull-left voti-proposti-giudizio-wizard " + ($("#bLocked").val() === "1" || r === "RC" ? "invisible" : ""),
                            callback: function () {
                                return !1
                            }
                        }
                    }
                });
                f.css({
                    "z-index": "1045", top: "50%", "margin-top": function () {
                        return -(f.height() / 2)
                    }
                });
                f.on("shown.bs.modal", function () {
                    $("#fsGiudizio").focus();
                    $(".voti-proposti-giudizio-wizard").unbind("click").click(function (n) {
                        n.preventDefault();
                        var t = "../Secret/APP_Ajax_Get.aspx?Action=VOTI_PROPOSTI_GIUDIZIO_WIZARD&Others=" + $(u).closest("tr").data("others") + "|" + $("#sTabGiud").val() + "|4096";
                        $.ajax({
                            type: "GET", cache: !1, url: t, async: !0, datatype: "html", success: function (n) {
                                handleModal.openModal(n);
                                $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                                    QuadroRiepilogativo.initWizardGiudizio()
                                })
                            }, error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    })
                })
            });
            $(".voti-proposti-annotazioni").unbind("click").click(function (t) {
                var i, r;
                t.preventDefault();
                i = $(this);
                n = i.data("row");
                r = bootbox.dialog({
                    title: "Annotazioni per l'alunno/a: " + $(this).closest("tr").data("nome"),
                    message: '<div class="bootstrap row"><div class="col-md-12"><div class="form-group"><textarea class="form-control" name="fsAnnotazioni" id="fsAnnotazioni" rows="5" maxlength="4096" ' + ($("#bLocked").val() === "1" ? "readonly" : "") + ">" + $("#AnnotazioniFinale\\#R" + n).val() + "<\/textarea><\/div><\/div><\/div>",
                    closeButton: !0,
                    onEscape: !0,
                    buttons: {
                        confirm: {
                            label: "<i class='fa fa-check'><\/i> Salva",
                            className: "btn-primary " + ($("#bLocked").val() === "1" ? "invisible" : ""),
                            callback: function () {
                                $("#AnnotazioniFinale\\#R" + n).val($("#fsAnnotazioni").val().trim());
                                $("#fsAnnotazioni").val().trim() === "" ? ($(i).removeClass("btn-success"), $(i).addClass("btn-warning")) : ($(i).removeClass("btn-warning"), $(i).addClass("btn-success"))
                            }
                        }, cancel: {label: "<i class='fas fa-times'><\/i> Chiudi", className: "btn-default"}
                    }
                });
                r.css({
                    top: "50%", "margin-top": function () {
                        return -(r.height() / 2)
                    }
                });
                r.on("shown.bs.modal", function () {
                    $("#fsAnnotazioni").focus()
                })
            })
        }, initVotiPropostiGiudizi: function () {
            $(document).ready(function () {
                handleInputField.init(!1, !1, !1, !1, !0, !0);
                QuadroRiepilogativo.initDettaglioVoti();
                $(".voti-proposti-giudizio-wizard").unbind("click").click(function (n) {
                    n.preventDefault();
                    var i = "../Secret/APP_Ajax_Get.aspx?Action=VOTI_PROPOSTI_GIUDIZIO_WIZARD&Others=";
                    i += $(this).closest("tr").data("others") + "|" + $(this).data("tab") + "|4096";
                    t = "#" + $(this).data("id");
                    $.ajax({
                        type: "GET", cache: !1, url: i, async: !0, datatype: "html", success: function (n) {
                            handleModal.openModal(n);
                            $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                                QuadroRiepilogativo.initWizardGiudizio()
                            })
                        }, error: function (n, t, i) {
                            SweetMessage.error(i.toString())
                        }
                    })
                });
                $("#ContentPlaceHolderMenu_ButtonSalva").unbind("click").click(function (n) {
                    var i, r, t, u;
                    n.preventDefault();
                    i = $(this).data("action");
                    i !== undefined && (r = $(this).attr("href") + "?Action=" + i, ApplyWait(), t = {}, t.fsGuids = $("#fsGuids").val(), $("tr[id^='Alu']").each(function () {
                        var n = $(this).data("row");
                        ($("#fsCons" + n).attr("changed") === "true" || $("#fsComp" + n).attr("changed") === "true" || $("#fsGiud" + n).attr("changed") === "true") && (t[$(this).attr("id")] = $(this).data("others"), $("#fsCons" + n).attr("changed") === "true" && (t["fsCons" + n] = $("#fsCons" + n).val()), $("#fsComp" + n).attr("changed") === "true" && (t["fsComp" + n] = $("#fsComp" + n).val()), $("#fsGiud" + n).attr("changed") === "true" && (t["fsGiud" + n] = $("#fsGiud" + n).val()))
                    }), u = JSON.stringify(t), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (bSave = !1, $(".form-control").removeAttr("changed"), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                });
                $("#ContentPlaceHolderMenu_ButtonStampa").unbind("click").click(function (n) {
                    var t, r, i, u;
                    n.preventDefault();
                    t = $(this).data("action");
                    t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.fsGuids = $("#fsGuids").val(), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
                })
            })
        }, initSchedaCarenzaModal: function (t) {
            handleInputField.init(!0, !1, !1, !1, !0);
            $("#fsModalita").select2({placeholder: "Selezionare le modalitÃ  di recupero..."});
            $("#modal-stack-" + iStackModal + " .modal-body").css("overflow-y", "auto");
            $("#modal-stack-" + iStackModal + " .modal-body").css("max-height", $(window).height() * .75);
            $(i).closest("tr").attr("data-id", t);
            $(".scheda-carenze-salva").unbind("click").click(function (t) {
                var r, u, f, e, o;
                (t.preventDefault(), r = $("#form-carenza"), r.length === 0 || r.validate({
                    focusInvalid: !1,
                    invalidHandler: function (n, t) {
                        t.numberOfInvalids() && $(".modal-body").scrollTop(0)
                    }
                }).form()) && (u = $(this).data("action"), u !== undefined && (f = $(this).attr("href") + "?Action=" + u, ApplyWait(), e = r.serializeObject(), o = JSON.stringify(e), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: f,
                    data: o,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (t) {
                        var u, r;
                        HideWait();
                        t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (u = $("#modal-stack-" + iStackModal), u.modal("hide"), r = JSON.parse(t.json), $("[id='uiTRec#R" + n + "']").val(r.uiTRecId), $("[id='chkRC#" + n + "']").val(r.fsRecuperata), $(i).removeClass("btn-warning").addClass("btn-success"), Notific8.success("Operazione eseguita correttamente."))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            });
            $(".scheda-carenze-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), r = $(this).attr("href") + "?Action=" + t;
                SweetMessage.confirm("Confermi la cancellazione della scheda carenza?", function () {
                    var n, u;
                    t !== undefined && (n = {}, n.carenzaId = $("#carenzaId").val(), u = JSON.stringify(n), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                $(i).removeClass("btn-success").addClass("btn-warning");
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initWizardGiudizio: function () {
            handleInputField.init(!1, !1, !1, !1, !0);
            $("#fsCompGiud").val($(t).val());
            $(t).val() !== "" && bootbox.alert({
                message: "<b>ATTENZIONE!<\/b><br><br>Esiste giÃ  un giudizio compilato, eventuali modifiche lo sovrascriveranno.",
                backdrop: !0
            });
            $("input[name^=fbFrase], #chkPunto, #chkInvio").unbind("change").change(function () {
                r()
            });
            $(".wizard-giudizio-pulisci").unbind("click").click(function (n) {
                n.preventDefault();
                $("input[name^=fbFrase][value='']").prop("checked", !0);
                r()
            });
            $(".wizard-giudizio-salva").unbind("click").click(function (n) {
                n.preventDefault();
                $(t).val($("#fsCompGiud").val());
                $(t).attr("changed", "true");
                REOnChange();
                $("#modal-stack-" + iStackModal).modal("hide")
            })
        }
    }
}(), AlunnoDettaglio = function () {
    var n;
    return {
        initQuadro: function () {
            $("#btnGriglia").on("click", function () {
                $("#elencoVoti").hide();
                $("#grigliaVoti").show()
            });
            $("#btnElenco").on("click", function () {
                $("#grigliaVoti").hide();
                $("#elencoVoti").show();
                n.draw()
            });
            $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"});
            $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                var i = $("#votiTab").DataTable({
                    scrollY: "250px",
                    scrollCollapse: !1,
                    paging: !1,
                    searching: !1,
                    ordering: !1,
                    info: !1
                }), t;
                n = $("#votiEle").DataTable({
                    scrollY: "295px",
                    scrollCollapse: !1,
                    paging: !1,
                    searching: !1,
                    ordering: !1,
                    info: !1
                });
                t = $("#noteTab").DataTable({
                    scrollY: "352px",
                    scrollCollapse: !1,
                    paging: !1,
                    searching: !1,
                    ordering: !1,
                    info: !1
                });
                $("#modal-stack-" + iStackModal).on("shown.bs.tab", function (n) {
                    n.target.href.endsWith("note") && t.draw()
                })
            })
        }
    }
}(), Comunicazioni = function () {
    function i(n, i) {
        $.ajax({
            type: "POST",
            cache: !1,
            url: n,
            data: i,
            dataType: "json",
            contentType: "json",
            async: !0,
            success: function (n) {
                if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                    var i = $("#modal-stack-" + iStackModal);
                    i.modal("hide");
                    t();
                    HideWait();
                    Notific8.success("Operazione eseguita correttamente.")
                }
            },
            error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    function n(n) {
        n || $("a", this).remove()
    }

    var t = function () {
        var t = $("#content-comunicazioni");
        $.ajax({
            type: "GET",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=COMUNICAZIONI_LISTA_REFRESH",
            async: !0,
            datatype: "html",
            success: function (i) {
                t.html(i);
                DownloadFileConHandler.DownloadFile();
                $("div.dotdotdot").dotdotdot({watch: !0, height: 80, after: "a.more", callback: n});
                $("div.dotdotdot").on("click", "a", function (t) {
                    if (t.preventDefault(), $(this).text() === "[+]") {
                        var i = $(this).closest("div.dotdotdot");
                        i.trigger("destroy");
                        $(i).find("a").text(" [-]")
                    } else $(this).text("[+]"), $(this).closest("div.dotdotdot").dotdotdot({
                        watch: !0,
                        height: 80,
                        after: "a.more",
                        callback: n
                    })
                })
            },
            error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    };
    return {
        init: function () {
            DownloadFileConHandler.DownloadFile();
            $(".check-read-message").unbind("click").click(function (n) {
                var t, u;
                n.preventDefault();
                var i = $(this).attr("data-action"), f = $(this).attr("data-id-message"),
                    e = $(this).attr("data-uiUteId"), o = $(this).attr("data-sClaId"), r = $(this).is(":checked"),
                    s = $(this);
                i != undefined && (t = $(this).attr("href") + "?Action=" + i, u = e + "|" + o + "|" + f + "|" + r, t = t + "&Others=" + u, $.ajax({
                    type: "POST",
                    cache: !1,
                    url: t,
                    async: !1,
                    datatype: "html",
                    success: function () {
                        r == !0 && s.hide()
                    },
                    error: function () {
                    }
                }))
            });
            $("#FiltroComunicazioni").unbind("change").change(function (n) {
                var t, r;
                n.preventDefault();
                var u = $(this).val(), i = $(this).attr("data-action"), f = $("#content-comunicazioni");
                i != undefined && (t = $(this).attr("href") + "?Action=" + i, r = u, t = t + "&Others=" + r, $.ajax({
                    type: "GET",
                    cache: !1,
                    url: t,
                    async: !1,
                    datatype: "html",
                    success: function (n) {
                        f.html(n)
                    },
                    error: function () {
                    }
                }))
            })
        }, initGestione: function () {
            DownloadFileConHandler.DownloadFile();
            $("div.dotdotdot").dotdotdot({watch: !0, height: 80, after: "a.more", callback: n});
            $("div.dotdotdot").on("click", "a", function (t) {
                if (t.preventDefault(), $(this).text() === "[+]") {
                    var i = $(this).closest("div.dotdotdot");
                    i.trigger("destroy");
                    $(i).find("a").text(" [-]")
                } else $(this).text("[+]"), $(this).closest("div.dotdotdot").dotdotdot({
                    watch: !0,
                    height: 80,
                    after: "a.more",
                    callback: n
                })
            });
            $("#tableCom").DataTable({paging: !1, searching: !1, ordering: !1, info: !1, fixedHeader: !0});
            $("#btnNewComunicazione").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, Ajx.getOpenModal(i, !1))
            });
            $("#content-comunicazioni").on("click", ".comunicazioni-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).data("others"), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        handleModal.openModal(n)
                    },
                    error: function (n, t, i) {
                        SweetMessage.error(i.toString())
                    }
                }))
            });
            $("#content-comunicazioni").on("click", ".comunicazioni-elimina-command", function (n) {
                n.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href"), u = $(this).data("others");
                SweetMessage.confirm("Vuoi cancellare la Comunicazione?", function () {
                    if (i != undefined) {
                        var n = r + "?Action=" + i;
                        n += "&Others=" + u;
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: n,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (n) {
                                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (t(), Notific8.success("Comunicazione eliminata correttamente."))
                            },
                            error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            })
        }, initComunicazioniModal: function () {
            DownloadFileConHandler.DownloadFile();
            handleInputField.init(!0, !1, !0, !0, !0);
            $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                $("#tableClassi").DataTable({
                    scrollY: "160px",
                    scrollCollapse: !1,
                    paging: !1,
                    searching: !1,
                    ordering: !1,
                    info: !1
                })
            });
            $("#chkAll").click(function () {
                $("td input:checkbox").not(this).prop("checked", this.checked)
            });
            $("#chkTut").on("switchChange.bootstrapSwitch", function (n, t) {
                $(".switch-mini").bootstrapSwitch("state", t)
            });
            $(".comunicazioni-salva").unbind("click").click(function (n) {
                var t, u, r, o, h;
                if ((n.preventDefault(), t = $("#form-comunicazione"), t.length == 0 || t.validate().form()) && (u = $(this).data("action"), u != undefined)) {
                    var s = $(this).attr("href") + "?Action=" + u, f = t.serializeObject(), e = $("#FileToUpload")[0];
                    if (e !== undefined && e.files.length > 0) {
                        if (r = e.files[0], r.size > 10485760) {
                            SweetMessage.error("Non Ã¨ possibile allegare file piÃ¹ grandi di 10Mb. Selezionare un allegato piÃ¹ piccolo.");
                            return
                        }
                        ApplyWait();
                        o = new FormData;
                        o.append(r.name, r);
                        $.ajax({
                            url: "../../Handlers/SD_UploadHandler.ashx?UseGuid=true&Type=CO",
                            type: "POST",
                            data: o,
                            contentType: !1,
                            processData: !1,
                            success: function (n) {
                                f.fsFiles = n;
                                var t = JSON.stringify(f);
                                i(s, t)
                            },
                            error: function (n, t, i) {
                                HideWait();
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        })
                    } else h = JSON.stringify(f), i(s, h)
                }
            });
            $(".comunicazioni-file-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href"), r = $(this).data("others");
                SweetMessage.confirm("Vuoi eliminare l'allegato dalla comunicazione?", function () {
                    var u, n, f;
                    t !== undefined && (u = i + "?Action=" + t, n = {}, n.fsOthers = r, f = JSON.stringify(n), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        dataType: "json",
                        async: !0,
                        success: function (n) {
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : $("#file_allegato").html(n.html)
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initComunicazioniCopiaModal: function () {
            $("#chkAll").click(function () {
                $("td input:checkbox").not(this).prop("checked", this.checked)
            });
            $(".comunicazioni-copia-salva").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                SweetMessage.confirm("Vuoi copiare la comunicazione nei plessi selezionati?<br/><br/><span class='small'>SarÃ  poi necessario andare in ogni plesso ed indicare in quali Classi rendere visibile la comunicazione<\/span>", function () {
                    if (t != undefined) {
                        var n = $("#form-comunicazione"), r = n.serializeObject(), u = JSON.stringify(r);
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: i,
                            data: u,
                            dataType: "json",
                            async: !0,
                            success: function (n) {
                                if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                    var t = $("#modal-stack-" + iStackModal);
                                    t.modal("hide");
                                    Notific8.success("Operazione eseguita correttamente.")
                                }
                            },
                            error: function (n, t, i) {
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        })
                    }
                })
            })
        }
    }
}(), ProgrammazioneScolastica = function () {
    return {
        initSelezione: function () {
            $(".programmazione-scolastica-selezione").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                ApplyWait();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $(this).attr("data-id"), Ajx.getOpenModal(i, !1))
            });
            $(".programmazione-scolastica-registro-elimina").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, SweetMessage.confirm("Vuoi cancellare l'attivitÃ  nel Registro?", function () {
                    var n, t;
                    ApplyWait();
                    n = {};
                    n.fsGuids = $("#fsGuids").val();
                    n.fuiRedMasterId = $("#fsAttivita").attr("data-id");
                    t = JSON.stringify(n);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        data: t,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($(".programmazione-scolastica-selezione").attr("data-id", ""), $("#fsAttivita").val(""), $(".programmazione-scolastica-registro-elimina").hide(), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }))
            })
        }, initSelezioneModal: function () {
            $("#modal-stack-" + iStackModal).on("shown.bs.modal", function () {
                var n = $("#programmazione .primary").offset(), t;
                n !== undefined && (t = n.top, $("#programmazione").animate({scrollTop: $("#programmazione").scrollTop() + t - 400}, 500, "linear"))
            });
            $(".clickable-row").unbind("click").click(function (n) {
                n.preventDefault();
                $(this).hasClass("primary") || ($(this).addClass("primary").siblings().removeClass("primary"), $(this).find("input").prop("checked", !0))
            });
            $(".programmazione-scolastica-seleziona").unbind("click").click(function (n) {
                var i, r, u, t, f;
                n.preventDefault();
                i = $("input[name=selPrg]:checked").val();
                i === undefined ? SweetMessage.error("Non Ã¨ stata selezionata alcuna programmazione. Devi prima selezionare la programmazione che vuoi utilizzare prima di procedere.") : (r = $(this).data("action"), r !== undefined && (u = $(this).attr("href") + "?Action=" + r, ApplyWait(), t = {}, t.fsGuids = $("#fsGuids").val(), t.fuiRedMasterId = $("#fsAttivita").attr("data-id"), t.fuiArgomentoId = $("#fsArgomento").attr("data-id"), t.fsArgomento = $("#fsArgomento").val(), t.txtData = $("#ContentPlaceHolderBody_txtDataSelezionataCAL").val(), t.selPrg = i, t.chkCopy = $("#chkCopy").prop("checked") ? "1" : "0", f = JSON.stringify(t), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: u,
                    data: f,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        var i, t;
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (i = $("#modal-stack-" + iStackModal), i.modal("hide"), t = JSON.parse(n.json), $("#fuiRedMasterId").attr("data-id", t.fuiRedMasterId), $(".programmazione-scolastica-selezione").attr("data-id", t.selPrg), $("#fsAttivita").val(t.fsAttivita), $(".programmazione-scolastica-registro-elimina").show(), t.fuiArgomentoId !== undefined && ($("#fsArgomento").attr("data-id", t.fuiArgomentoId), $("#fsArgomento").val(t.fsArgomento)), Notific8.success("Operazione eseguita correttamente."))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            })
        }
    }
}(), LibriDiTesto = function () {
    return {
        initProposte: function () {
            $("#btnNewLibro").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_ddlClasse").val()), Ajx.getOpenModal(i, !1))
            });
            $(".export-libri-di-testo").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.classeId = Base64.encode($("#ContentPlaceHolderBody_ddlClasse").val()), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
            });
            $(".libri-di-testo-proposte-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $(".libri-di-testo-proposte-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href"), r = $(this).closest("tr").data("id");
                SweetMessage.confirm("Vuoi cancellare la proposta di adozione?", function () {
                    var u, n, f;
                    t != undefined && (u = i + "?Action=" + t, n = {}, n.fsOthers = r, f = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (DoPostBack("REFRESH"), Notific8.success("Proposta eliminata correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                })
            })
        }, initProposteModal: function () {
            handleInputField.init(!1, !1, !1, !0, !1, !1);
            $("#fiOpera").select2("destroy");
            $("#fiOpera").select2({
                placeholder: "Seleziona un libro ...",
                minimumInputLength: 4,
                ajax: {
                    url: "../Secret/APP_Ajax_Get.aspx?Action=SEEKER_LIBRO",
                    dataType: "json",
                    quietMillis: 250,
                    data: function (n) {
                        return {q: n}
                    },
                    results: function (n) {
                        return {results: n}
                    },
                    cache: !0
                },
                templateResult: function (n) {
                    return $(n.text)
                },
                templateSelection: function (n) {
                    return $(n.text)
                },
                escapeMarkup: function (n) {
                    return n
                },
                initSelection: function (n, t) {
                    if (n.val() !== "") {
                        var i = JSON.parse(Base64.decode(n.val()));
                        t(i)
                    }
                }
            });
            $("#fiOpera").on("change", function (n) {
                var t, i;
                n.preventDefault();
                $("#content-libro").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>');
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + n.val, ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        $("#content-libro").html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        $("#content-libro").html("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $(".libri-di-testo-proposte-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-proposta"), t.length === 0 || t.validate({ignore: []}).form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                DoPostBack("REFRESH");
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }
    }
}(), MaterialeDidattico = function () {
    function t() {
        var t = {}, i;
        t.uiDocId = $("#ContentPlaceHolderBody_ddlDocente").val();
        i = JSON.stringify(t);
        ApplyWait();
        $.ajax({
            type: "POST",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=MATERIALE_DIDATTICO_FOLDERS",
            data: i,
            async: !1,
            datatype: "json",
            success: function (t) {
                HideWait();
                t.length === 0 ? ($("#container_folder").html("<h5><b>Non ci sono cartelle.<\/b><\/h5><h5>Cliccare sul pulsante 'Nuova Cartella' per inserire una cartella.<\/h5>"), $(".treeview-expand-all").hide(), $(".treeview-collapse-all").hide(), $(".materiale-didattico-folder-modifica").hide(), $(".materiale-didattico-folder-elimina").hide()) : ($("#container_folder").treeview({
                    data: t,
                    levels: 1,
                    showTags: !0,
                    expandIcon: "fa fa-chevron-right",
                    collapseIcon: "fa fa-chevron-down",
                    onNodeSelected: function (t, i) {
                        $("#content_empty").hide();
                        $("#container_content").show();
                        n(i.id)
                    },
                    onNodeUnselected: function () {
                        $("#container_content").html("");
                        $("#container_content").hide();
                        $("#content_empty").show()
                    }
                }), $(".treeview-expand-all").show(), $(".treeview-collapse-all").show(), $(".materiale-didattico-folder-modifica").show(), $(".materiale-didattico-folder-elimina").show())
            },
            error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    function i(n) {
        for (var u = $("#container_folder").treeview("findNodes", ["^" + n + "$", "id"])[0], r = [], t = $("#container_folder").treeview("getParents", u)[0], i; t !== undefined;) r.push(t), t = $("#container_folder").treeview("getParents", t)[0];
        for (i = r.length - 1; i >= 0; i--) $("#container_folder").treeview("expandNode", r[i]);
        $("#container_folder").treeview("selectNode", u)
    }

    function n(n) {
        var t = {}, i;
        t.uiDocId = $("#ContentPlaceHolderBody_ddlDocente").val();
        t.fiFolderId = n;
        i = JSON.stringify(t);
        ApplyWait();
        $.ajax({
            type: "POST",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=MATERIALE_DIDATTICO_FOLDER_CONTENTS",
            data: i,
            async: !0,
            datatype: "html",
            success: function (n) {
                HideWait();
                $("#container_content").html(n)
            },
            error: function (n, t, i) {
                HideWait();
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    return {
        initGestione: function () {
            $(document).ready(function () {
                t()
            });
            $("#ContentPlaceHolderBody_ddlDocente").unbind("change").change(function () {
                setTimeout("__doPostBack('ctl00$ContentPlaceHolderBody$ddlDocente','')", 0)
            });
            $(".treeview-expand-all").unbind("click").click(function (n) {
                n.preventDefault();
                $("#container_folder").treeview(!0).collapseAll();
                $("#container_folder").treeview(!0).expandAll()
            });
            $(".treeview-collapse-all").unbind("click").click(function (n) {
                n.preventDefault();
                $("#container_folder").treeview(!0).collapseAll()
            });
            $(".materiale-didattico-folder-inserimento").unbind("click").click(function (n) {
                var i, t, r, u;
                if (n.preventDefault(), i = $(this).data("action"), i != undefined) {
                    t = 0;
                    try {
                        r = $("#container_folder").treeview(!0).getSelected();
                        r.length === 1 && (t = r[0].id)
                    } catch (n) {
                        t = 0
                    }
                    u = $(this).attr("href") + "?Action=" + i;
                    u += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + t;
                    Ajx.getOpenModal(u, !1)
                }
            });
            $(".materiale-didattico-folder-modifica").unbind("click").click(function (n) {
                var t, i, r, u;
                if (n.preventDefault(), t = $(this).data("action"), t != undefined) {
                    if (i = 0, r = $("#container_folder").treeview(!0).getSelected(), r.length === 1) i = r[0].id; else {
                        SweetMessage.error("Nessuna cartella selezionata. Cliccare su una cartella per selezionarla.");
                        return
                    }
                    u = $(this).attr("href") + "?Action=" + t;
                    u += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + i;
                    Ajx.getOpenModal(u, !1)
                }
            });
            $(".materiale-didattico-folder-elimina").unbind("click").click(function (n) {
                var t, i, r;
                if (n.preventDefault(), t = $("#container_folder").treeview(!0).getSelected(), t.length === 1) {
                    if (t[0].nodes !== undefined && t[0].nodes.length > 0) {
                        SweetMessage.error("<b>Attenzione!!!<\/b><br>Non Ã¨ possibile eliminare una cartella che ha delle sottocartelle.");
                        return
                    }
                } else {
                    SweetMessage.error("Nessuna cartella selezionata. Cliccare su una cartella per selezionarla.");
                    return
                }
                i = $(this).data("action");
                r = $(this).attr("href");
                SweetMessage.confirm("Vuoi cancellare la Cartella?", function () {
                    var u, n, f;
                    i !== undefined && (u = r + "?Action=" + i, n = {}, n.fiFolderId = t[0].id, f = JSON.stringify(n), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($("#container_folder").treeview("removeNode", t[0]), Notific8.success("Cartella eliminata correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                })
            })
        }, initFolderModal: function () {
            $("#fsNote").val(Base64.decode($("#fsNote").val()));
            handleInputField.init(!1, !1, !0, !1, !0);
            $("#fsNote").summernote({
                height: 240,
                lang: "it-IT",
                toolbar: [["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]]
            });
            $("#fuiDocCla").select2({placeholder: "Seleziona le classi..."});
            $("#fuiDocMat").select2({placeholder: "Seleziona le materie..."});
            $("#fuiDocIds").select2({placeholder: "Seleziona i docenti..."});
            $("#fuiAluCla").select2({placeholder: "Seleziona le classi degli alunni..."});
            $("#fuiAluMat").select2({placeholder: "Seleziona le materie degli alunni..."});
            $("#fuiAluIds").select2({
                placeholder: "Seleziona gli alunni...",
                multiple: !0,
                minimumInputLength: 3,
                ajax: {
                    url: "../Secret/APP_Ajax_Get.aspx?Action=SEEKER_ALUNNO",
                    dataType: "json",
                    quietMillis: 250,
                    data: function (n) {
                        return {q: n}
                    },
                    results: function (n) {
                        return {results: n}
                    },
                    cache: !1
                },
                initSelection: function (n, t) {
                    if (n.val() !== "") {
                        var i = JSON.parse(Base64.decode(n.val()));
                        t(i)
                    }
                }
            });
            $(".materiale-didattico-folder-salva").unbind("click").click(function (n) {
                var r, f, e, u, o;
                if (n.preventDefault(), r = $("#form-folder"), r.length === 0 || r.validate().form()) {
                    if ($("#fsNote").val().length > 131072) {
                        SweetMessage.error("E' stata superata la dimensione massima del campo note. Ridurre la quantitÃ  del testo inserito. Verificare.");
                        return
                    }
                    f = $(this).data("action");
                    f != undefined && (e = $(this).attr("href") + "?Action=" + f, u = r.serializeObject(), u.fsNote = Base64.encode(u.fsNote), o = JSON.stringify(u), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: e,
                        data: o,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            var r, u;
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (r = $("#modal-stack-" + iStackModal), r.modal("hide"), u = JSON.parse(n.json), t(), i(u.fiFolderId), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                }
            })
        }, initFolderContent: function () {
            DownloadFileConHandler.DownloadFile();
            $(".materiale-didattico-testo").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            });
            $(".materiale-didattico-content-inserimento").unbind("click").click(function (n) {
                var t, i, r, u;
                if (n.preventDefault(), t = $(this).data("action"), t != undefined) {
                    if (i = 0, r = $("#container_folder").treeview(!0).getSelected(), r.length === 1) i = r[0].id; else {
                        SweetMessage.error("Nessuna cartella selezionata. Cliccare su una cartella per selezionarla.");
                        return
                    }
                    u = $(this).attr("href") + "?Action=" + t;
                    u += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + i;
                    Ajx.getOpenModal(u, !1)
                }
            });
            $(".materiale-didattico-content-modifica").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            });
            $(".materiale-didattico-content-elimina").unbind("click").click(function (t) {
                t.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href"),
                    u = $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + $(this).closest("tr").data("others");
                SweetMessage.confirm("Vuoi cancellare il Contenuto?", function () {
                    var f, t, e;
                    i != undefined && (f = r + "?Action=" + i, t = {}, t.fsOthers = u, e = JSON.stringify(t), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: f,
                        data: e,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (t) {
                            if (t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#container_folder").treeview(!0).getSelected();
                                i.length === 1 && n(i[0].id);
                                Notific8.success("Contenuto eliminato correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error(i.toString())
                        }
                    }))
                })
            })
        }, initContentModal: function () {
            DownloadFileConHandler.DownloadFile();
            $("#fsTesto").val(Base64.decode($("#fsTesto").val()));
            handleInputField.init(!1, !1, !0, !0, !0);
            $("#fsTesto").summernote({
                height: 275,
                lang: "it-IT",
                toolbar: [["font", ["bold", "italic", "underline", "superscript", "subscript", "strikethrough", "clear"]], ["fontname", ["fontname"]], ["fontsize", ["fontsize"]], ["color", ["color"]], ["para", ["ul", "ol", "paragraph"]], ["height", ["height"]], ["table", ["table"]], ["view", ["fullscreen"]]]
            });
            $(".materiale-didattico-content-salva").unbind("click").click(function (t) {
                var r, e, o, i, u, f, s, h;
                if (t.preventDefault(), r = $("#form-content"), r.length === 0 || r.validate().form()) {
                    if ($("#fsTesto").val().length > 131072) {
                        SweetMessage.error("E' stata superata la dimensione massima del campo testo. Ridurre la quantitÃ  del testo inserito. Verificare.");
                        return
                    }
                    if (e = $(this).data("action"), e != undefined) if (o = $(this).attr("href") + "?Action=" + e, i = r.serializeObject(), i.fsTesto = Base64.encode(i.fsTesto), u = $("#FileToUpload")[0], u !== undefined && u.files.length > 0) {
                        if (f = u.files[0], f.size > 10485760) {
                            SweetMessage.error("Non Ã¨ possibile allegare file piÃ¹ grandi di 10Mb. Selezionare un allegato piÃ¹ piccolo.");
                            return
                        }
                        ApplyWait();
                        s = new FormData;
                        s.append(f.name, f);
                        $.ajax({
                            url: "../../Handlers/SD_UploadHandler.ashx?UseGuid=true&Type=MD",
                            type: "POST",
                            data: s,
                            contentType: !1,
                            processData: !1,
                            success: function (t) {
                                i.fsFiles = t;
                                var r = JSON.stringify(i);
                                $.ajax({
                                    type: "POST",
                                    cache: !1,
                                    url: o,
                                    data: r,
                                    dataType: "json",
                                    contentType: "json",
                                    async: !0,
                                    success: function (t) {
                                        var r, i;
                                        t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (r = $("#modal-stack-" + iStackModal), r.modal("hide"), i = $("#container_folder").treeview(!0).getSelected(), i.length === 1 && n(i[0].id), Notific8.success("Operazione eseguita correttamente."))
                                    },
                                    error: function (n, t, i) {
                                        HideWait();
                                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                                    }
                                })
                            },
                            error: function (n, t, i) {
                                HideWait();
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        })
                    } else h = JSON.stringify(i), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: o,
                        data: h,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            var r, i;
                            t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (r = $("#modal-stack-" + iStackModal), r.modal("hide"), i = $("#container_folder").treeview(!0).getSelected(), i.length === 1 && n(i[0].id), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".materiale-didattico-content-file-elimina").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href"), r = $(this).data("others");
                SweetMessage.confirm("Vuoi eliminare l'allegato dal contenuto?", function () {
                    var u, n, f;
                    t !== undefined && (u = i + "?Action=" + t, n = {}, n.fsOthers = r, f = JSON.stringify(n), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : $("#file_allegato").html(n.html)
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initShared: function () {
            $("#ContentPlaceHolderBody_ddlDocente").unbind("change").change(function (n) {
                n.preventDefault();
                var t = "../Secret/APP_Ajax_Get.aspx?Action=MATERIALE_DIDATTICO_SHARED";
                t += "&Others=" + $(this).val();
                $("#ContentPlaceHolderBody_divContent").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>');
                $.ajax({
                    type: "GET", cache: !1, url: t, async: !0, datatype: "html", success: function (n) {
                        $("#ContentPlaceHolderBody_divContent").html(n)
                    }, error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#ContentPlaceHolderBody_divContent").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                })
            })
        }, initSharedContent: function () {
            $(".materiale-didattico-shared-copia").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            })
        }, initSharedCopiaModal: function () {
            $(".materiale-didattico-shared-copia-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-shared-copia"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    SweetMessage.confirm("Confermi la copia del contenuto?", function () {
                        i != undefined && $.ajax({
                            type: "POST",
                            cache: !1,
                            url: r,
                            data: f,
                            dataType: "json",
                            contentType: "json",
                            async: !0,
                            success: function (n) {
                                if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                    var t = $("#modal-stack-" + iStackModal);
                                    t.modal("hide");
                                    Notific8.success("Operazione eseguita correttamente.")
                                }
                            },
                            error: function (n, t, i) {
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        })
                    })
                }
            })
        }
    }
}(), ProgrammazioneDidattica = function () {
    return {
        init: function () {
            $(".listbox_classi").unbind("click").click(function () {
                var n = $("#" + $(this).attr("data-action-content"));
                n.html("")
            });
            $(".refresh-prog-did-docenti").unbind("click").click(function (n) {
                var r;
                n.preventDefault();
                var i = $(this).attr("data-action"), u = $("#" + $(this).attr("data-action-content")), t,
                    f = $(".listbox_classi");
                if ($(f).each(function () {
                    $(this).prop("checked") && (t = t == undefined ? '{fgClassId:"' + $(this).attr("data-id-classe") + '"}' : t + ',{fgClassId:"' + $(this).attr("data-id-classe") + '"}')
                }), t == undefined) {
                    alert("E' necessario selezionare almeno una classe.");
                    return
                }
                ApplyWait();
                t = "[" + t + "]";
                i != undefined && (r = $(this).attr("href") + "?Action=" + i, $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    data: t,
                    async: !1,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        u.html(n)
                    },
                    error: function () {
                        HideWait()
                    }
                }))
            })
        }
    }
}(), LegendaVoti = function () {
    return {
        init: function () {
            $(".imgLegendaVoti").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, Ajx.getOpenModal(i, !1))
            })
        }, close: function () {
            document.getElementById("idLayerLegenda").style.display = "none";
            ApplyOpacity(0)
        }
    }
}(), Permessi = function () {
    function n() {
        var n = "../Secret/APP_Ajax_Get.aspx?Action=PERMESSI_LISTA_REFRESH", t;
        n += "&Others=" + $("#ContentPlaceHolderBody_ddlClasse").val();
        t = $("#content-permessi");
        $.ajax({
            type: "GET", cache: !1, url: n, async: !0, datatype: "html", success: function (n) {
                t.html(n);
                $("#tablePer").DataTable({paging: !1, searching: !1, ordering: !1, info: !1, fixedHeader: !0})
            }, error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }

    return {
        init: function () {
            $("#btnNewPermesso").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlClasse").val(), Ajx.getOpenModal(i, !1))
            });
            $("#tablePer").DataTable({paging: !1, searching: !1, ordering: !1, info: !1, fixedHeader: !0});
            $("#content-permessi").on("click", ".permessi-modifica-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            });
            $("#content-permessi").on("click", ".permessi-elimina-command", function (t) {
                t.preventDefault();
                var r = $(this).data("action"), f = $(this).attr("href"), e = $(this).closest("tr").data("others"),
                    i = $(this).closest("tr").data("auth"), u = "";
                i != undefined && parseInt(i) > 0 && (u = "<small><b>Attenzione! Ci sono " + i + " genitori che hanno giÃ  dato l'autorizzazione, anche le autorizzazioni verrano eliminate.<\/b><\/small><br><br>");
                SweetMessage.confirm(u + "Vuoi cancellare il permesso autorizzato?", function () {
                    if (r != undefined) {
                        var t = f + "?Action=" + r;
                        t += "&Others=" + e;
                        ApplyWait();
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: t,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (t) {
                                HideWait();
                                t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (n(), Notific8.success("Permesso eliminato correttamente."))
                            },
                            error: function (n, t, i) {
                                HideWait();
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            });
            $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
        }, initPermessiModal: function () {
            handleInputField.init(!0, !0, !1, !0, !0);
            $("#fuiAlunniIds").select2({placeholder: "Seleziona uno o piÃ¹ alunni...", allowClear: !0});
            $("#fiGiorni").select2({placeholder: "Seleziona uno o piÃ¹ giorni...", allowClear: !0});
            $("#fbAll").on("switchChange.bootstrapSwitch", function (n, t) {
                $("#fuiAlunniIds").select2("enable", t === !1);
                $("#fuiAlunniIds").select2("val", "")
            });
            $("#fsTipo").change(function () {
                $(this).val() === "A" ? ($("#fsOraLez").attr("disabled", !0), $("#ftOrario").attr("disabled", !0)) : ($("#fsOraLez").attr("disabled", !1), $("#ftOrario").attr("disabled", !1))
            });
            $("#fsTipo").change();
            $("#fdIni_dtp").on("dp.change", function () {
                $("#fdEnd").val($("#fdIni").val())
            });
            $(".permessi-salva").unbind("click").click(function (t) {
                var i, r;
                if ((t.preventDefault(), i = $("#form-permesso"), i.length == 0 || i.validate({
                    rules: {
                        fsOraLez: {
                            require_from_group: {
                                param: [1, ".group-ora"],
                                depends: function () {
                                    return $("#fsTipo").val() !== "A"
                                }
                            }
                        }, ftOrario: {
                            require_from_group: {
                                param: [1, ".group-ora"], depends: function () {
                                    return $("#fsTipo").val() !== "A"
                                }
                            }
                        }
                    },
                    messages: {
                        fsOraLez: {require_from_group: $.validator.format("Inserisci almeno l'ora di lezione o l'orario.")},
                        ftOrario: {require_from_group: $.validator.format("Inserisci almeno l'ora di lezione o l'orario.")}
                    },
                    groups: {orario: "fsOraLez ftOrario"}
                }).form()) && (r = $(this).data("action"), r != undefined)) {
                    var u = $(this).attr("href") + "?Action=" + r, f = i.serializeObject(), e = JSON.stringify(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                n();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initRichieste: function () {
            $("#content-richieste").on("shown.bs.tab", function (n) {
                var u = $(n.target).attr("href"), t, i, r;
                u === "#da_auth" ? DoPostBack("REFRESH") : (t = $(u), t.html() === "" && (i = t.data("action"), i !== undefined && (r = t.attr("href") + "?Action=" + i, r += "&Others=" + t.data("others"), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: r,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        t.html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                }))))
            });
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=chkRic]").not(this).prop("checked", this.checked)
            });
            $(".permessi-richieste-autorizza").unbind("click").click(function (n) {
                var t, i;
                if (n.preventDefault(), $("td input:checkbox[id^=chkRic]:checked").length === 0) {
                    SweetMessage.error("Nessun richiesta Ã¨ stata selezionata.<br>E' necesessario prima selezionare le richieste da autorizzare.");
                    return
                }
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, Ajx.getOpenModal(i))
            });
            $(".permessi-richieste-nega").unbind("click").click(function (n) {
                if (n.preventDefault(), $("td input:checkbox[id^=chkRic]:checked").length === 0) {
                    SweetMessage.error("Nessun richiesta Ã¨ stata selezionata.<br>E' necesessario prima selezionare le richieste da autorizzare.");
                    return
                }
                var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                swal({
                    title: "Nega autorizzazione",
                    text: "Inserisci il Motivo",
                    type: "question",
                    input: "text",
                    inputAttributes: {autocapitalize: "off", autocorrect: "off"},
                    showCancelButton: !0,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;Conferma",
                    cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;Annulla",
                    preConfirm: function (n) {
                        return new Promise(function (t, i) {
                            n.trim() === "" ? i("Inserisci una motivazione.") : t()
                        })
                    }
                }).then(function (n) {
                    var r, u;
                    t !== undefined && (r = {}, r.fsNote = n, $("td input:checkbox[id^=chk]:checked").each(function () {
                        r[this.id] = $(this).val()
                    }), u = JSON.stringify(r), ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        data: u,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : DoPostBack("REFRESH")
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                }).done()
            })
        }, initRichiesteAutorizzaModal: function () {
            handleInputField.init(!1, !1, !1, !0, !0, !1);
            $(".permessi-richieste-autorizza-salva").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                SweetMessage.confirm("Confermi l'approvazione delle richieste selezionate?", function () {
                    var n, r;
                    t !== undefined && (n = $("#form-autorizza").serializeObject(), $("td input:checkbox[id^=chk]:checked").each(function () {
                        n[this.id] = $(this).val()
                    }), r = JSON.stringify(n), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        data: r,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                Notific8.success("Operazione eseguita correttamente.");
                                DoPostBack("REFRESH")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }
    }
}(), Colloqui = function () {
    var t = function () {
        var n = "../Secret/APP_Ajax_Get.aspx?Action=COLLOQUI_PERIODI_REFRESH", t;
        n += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val();
        t = $("#content-configurazione");
        $.ajax({
            type: "GET", cache: !1, url: n, async: !0, datatype: "html", success: function (n) {
                t.html(n)
            }, error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    }, n = function () {
        var n = "../Secret/APP_Ajax_Get.aspx?Action=COLLOQUI_PRENOTAZIONI_REFRESH", t;
        n += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val() + "|" + $("#ContentPlaceHolderBody_txtDaData").val();
        t = $("#content-prenotazioni");
        $.ajax({
            type: "GET", cache: !1, url: n, async: !0, datatype: "html", success: function (n) {
                t.html(n)
            }, error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    };
    return {
        init: function () {
            $("#btnNewColloquio").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val(), Ajx.getOpenModal(i, !1))
            });
            $("#btnEmail").unbind("click").click(function (n) {
                var t;
                if (n.preventDefault(), t = $(this).data("action"), t != undefined) {
                    var i = $(this).attr("href") + "?Action=" + t,
                        r = $("#formMasterRegistroElettronico").serializeObject(), u = JSON.stringify(r);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        async: !0,
                        data: u,
                        datatype: "html",
                        success: function (n) {
                            handleModal.openModal(n)
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $("#btnNewRicevimento").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val(), Ajx.getOpenModal(i, !1))
            });
            $("#content-configurazione").on("click", ".colloqui-periodo-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).data("others"), Ajx.getOpenModal(i, !1))
            });
            $("#content-configurazione").on("click", ".colloqui-periodo-elimina", function (n) {
                n.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href"), u = $(this).data("others");
                SweetMessage.confirm("Vuoi cancellare il periodo di ricevimento?", function () {
                    if (i != undefined) {
                        var n = r + "?Action=" + i;
                        n += "&Others=" + u;
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: n,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (n) {
                                n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (t(), Notific8.success("Ricevimento eliminato correttamente."))
                            },
                            error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            });
            $("#content-prenotazioni").on("click", ".colloqui-prenotazioni-esito", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).data("others"), Ajx.getOpenModal(i, !1))
            });
            $("#content-prenotazioni").on("click", ".colloqui-prenotazioni-elimina", function (t) {
                t.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href"), u = $(this).data("others");
                SweetMessage.confirm("Vuoi cancellare la prenotazione del colloquio?", function () {
                    if (i != undefined) {
                        var t = r + "?Action=" + i;
                        t += "&Others=" + u;
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: t,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (t) {
                                t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (n(), Notific8.success("Prenotazione eliminata correttamente."))
                            },
                            error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            })
        }, initColloquiModal: function () {
            $(".seeker-alunno").select2("destroy");
            $(".seeker-alunno").select2({
                placeholder: "Seleziona un alunno ...",
                allowClear: !0,
                minimumInputLength: 3,
                ajax: {
                    url: "../Secret/APP_Ajax_Get.aspx?Action=SEEKER_ALUNNO",
                    dataType: "json",
                    quietMillis: 250,
                    data: function (n) {
                        return {q: n}
                    },
                    results: function (n) {
                        return {results: n}
                    },
                    cache: !1
                }
            });
            $("#fuiAlunno").unbind("change").change(function (n) {
                var t, i, r, u;
                n.preventDefault();
                $("#content-disponibili").html("");
                $(".colloqui-inserimento-salva").prop("disabled", !0);
                t = $(this).val();
                i = $(this).data("action");
                i != undefined && t !== null && (r = $("#" + $(this).data("content")), u = $(this).attr("href") + "?Action=" + i, u += "&Others=" + t, $.ajax({
                    type: "GET",
                    cache: !1,
                    url: u,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        r.html(n);
                        $("#fuiGenitore").select2()
                    },
                    error: function (n, t, i) {
                        r.html("");
                        SweetMessage.error(i.toString())
                    }
                }))
            });
            $(".colloqui-cerca-disponibili").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-colloqui"), t.length === 0 || t.validate({ignore: []}).form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = $("#" + $(this).data("content")),
                        f = t.serializeObject(), e = JSON.stringify(f);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: e,
                        dataType: "html",
                        async: !0,
                        success: function (n) {
                            HideWait();
                            u.html(n)
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".colloqui-inserimento-salva").unbind("click").click(function (t) {
                var i, r;
                if ((t.preventDefault(), i = $("#form-colloqui"), i.length === 0 || i.validate({ignore: []}).form()) && (r = $(this).data("action"), r != undefined)) {
                    var u = $(this).attr("href") + "?Action=" + r, f = i.serializeObject(), e = JSON.stringify(f);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (HideWait(), t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                n();
                                Notific8.success("Colloqui prenotati correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initEmailModal: function () {
            handleInputField.init(!1, !1, !1, !0, !1);
            $(".colloqui-email-invia").unbind("click").click(function (n) {
                var t, i, r, u, f;
                (n.preventDefault(), t = $("#form-esito"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i != undefined && (r = $(this).attr("href") + "?Action=" + i, r += "&Others=" + $("#ContentPlaceHolderBody_ddlDocente").val(), u = t.serializeObject(), f = JSON.stringify(u), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    data: f,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                            var t = $("#modal-stack-" + iStackModal);
                            t.modal("hide");
                            Notific8.success("Invio eseguito correttamente.")
                        }
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            })
        }, initEsitiModal: function () {
            handleInputField.init(!1, !1, !1, !1, !0);
            $(".colloqui-esito-salva").unbind("click").click(function (t) {
                var i, r;
                if ((t.preventDefault(), i = $("#form-esito"), i.length === 0 || i.validate().form()) && (r = $(this).data("action"), r != undefined)) {
                    var u = $(this).attr("href") + "?Action=" + r, f = i.serializeObject(), e = JSON.stringify(f);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (HideWait(), t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                Notific8.success("Esito salvato.");
                                n()
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".colloqui-esito-elimina").unbind("click").click(function (t) {
                t.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href"), u = $("#fuiId").val();
                SweetMessage.confirm("Vuoi cancellare l'esito del colloquio?", function () {
                    if (i != undefined) {
                        var t = r + "?Action=" + i;
                        t += "&Others=" + u;
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: t,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (t) {
                                if (t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                    var i = $("#modal-stack-" + iStackModal);
                                    i.modal("hide");
                                    Notific8.success("Esito eliminato correttamente.");
                                    n()
                                }
                            },
                            error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            })
        }, initRicevimentoModal: function () {
            $(".decrementa-uno").unbind("click").click(function (n) {
                n.preventDefault();
                var t = parseInt($("#fiMax").val());
                t -= 1;
                t > 0 && $("#fiMax").val(t)
            });
            $(".incrementa-uno").unbind("click").click(function (n) {
                n.preventDefault();
                var t = parseInt($("#fiMax").val());
                t += 1;
                t > 0 && $("#fiMax").val(t)
            });
            handleInputField.init(!0, !0, !0, !0, !0);
            $("#fiRipetibile").unbind("change").change(function () {
                var n = $(this).val();
                n == "0" ? ($("#fdEnd").prop("disabled", !0), $("#fdEnd").val(""), $("#fiDay").prop("disabled", !0), $("#fdCloseIni").prop("disabled", !0), $("#fdCloseIni").val(""), $("#fdCloseEnd").prop("disabled", !0), $("#fdCloseEnd").val("")) : ($("#fdEnd").prop("disabled", !1), $("#fiDay").prop("disabled", !1), $("#fdCloseIni").prop("disabled", !1), $("#fdCloseEnd").prop("disabled", !1))
            });
            $("#fdIni_dtp").on("dp.change", function () {
                var t = parseDate($("#fdIni").val()), n;
                t !== null && (n = t.getDay(), n === 0 && (n = 7), n !== null && $("#fiDay").select2("val", n))
            });
            $("#fiRipetibile").change();
            $("#chkAll").click(function () {
                $("td input:checkbox").not(this).prop("checked", this.checked)
            });
            $(".colloqui-periodo-salva").unbind("click").click(function (n) {
                var i;
                if (n.preventDefault(), i = $("#form-ricevimento"), i.length == 0 || i.validate().form()) {
                    var r = $(this).data("action"), u = $("#form-ricevimento"), f = u.serializeObject(),
                        e = $(this).attr("href") + "?Action=" + r, o = JSON.stringify(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: e,
                        data: o,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                t();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }
    }
}(), RegistroDocente = function () {
    var n;
    return {
        init: function () {
            $(function () {
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
            });
            handleInputField.init(!1, !1, !1, !1, !1, !0);
            LegendaVoti.init();
            AlunnoScheda.init();
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=chk]").not(this).prop("checked", this.checked)
            });
            RegistroVoti.initVotiInserimentoModifica("docente");
            ProgrammazioneScolastica.initSelezione();
            $(".registro-docente-lezione").unbind("click").click(function (t) {
                var r, i;
                t.preventDefault();
                n = this;
                r = $(this).data("action");
                r != undefined && (i = $(this).attr("href") + "?Action=" + r, i += "&Others=" + $("#fsGuids").val(), i += "|" + $(this).closest("tr").data("aluid"), i += "|" + $("#fsOreLez").attr("data-id"), i += "|" + $("#ContentPlaceHolderBody_txtDataSelezionataCAL").val(), Ajx.getOpenModal(i, !1))
            });
            $(".registro-docente-salva").unbind("click").click(function (n) {
                var i, r, u, t, f;
                if (n.preventDefault(), i = !1, $("tr[id^='row']").each(function () {
                    var n = $(this).attr("id").substring(3);
                    (parseInt($("#fsOreAss" + n).val()) || 0) < (parseInt($("#fsOreAssNo" + n).val()) || 0) ? (i = !0, $("#fsOreAss" + n).focus(), $("#fsOreAss" + n).addClass("has-input-error"), $("#fsOreAssNo" + n).addClass("has-input-error")) : ($("#fsOreAss" + n).removeClass("has-input-error"), $("#fsOreAssNo" + n).removeClass("has-input-error"))
                }), i) {
                    SweetMessage.error("Le ore di assenza che non concorrono nel calcolo dell'esito non possono essere superiori a quelle totali.<br>Dati non salvati.");
                    return
                }
                r = $(this).data("action");
                r !== undefined && (ApplyWait(), u = $(this).attr("href") + "?Action=" + r, t = {}, t.fsGuids = $("#fsGuids").val(), t.fuiRedMasterId = $("#fsOreLez").attr("data-id"), t.txtData = $("#ContentPlaceHolderBody_txtDataSelezionataCAL").val(), $("tr[id^='row']").each(function () {
                    var n = $(this).attr("id").substring(3);
                    ($("#fsOreAss" + n).attr("changed") === "true" || $("#fsOreAssNo" + n).attr("changed") === "true") && (t[$(this).attr("id")] = $(this).data("aluid"), t["fsOreAss" + n] = $("#fsOreAss" + n).val(), t["fsOreAssNo" + n] = $("#fsOreAssNo" + n).val())
                }), $("#fsOreLez").attr("changed") === "true" && (t.fsOreLez = $("#fsOreLez").val()), $("#fsArgomento").attr("changed") === "true" && (t.fuiArgomentoId = $("#fsArgomento").attr("data-id"), t.fsArgomento = $("#fsArgomento").val()), $("#fsCompiti").attr("changed") === "true" && (t.fuiCompitiId = $("#fsCompiti").attr("data-id"), t.fsCompiti = $("#fsCompiti").val()), $("#fsNote").attr("changed") === "true" && (t.fuiNoteId = $("#fsNote").attr("data-id"), t.fsNote = $("#fsNote").val()), f = JSON.stringify(t), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: u,
                    data: f,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (n) {
                        n.errorcode !== "0" ? (HideWait(), SweetMessage.error(n.errormsg)) : (Notific8.success("Operazione eseguita correttamente."), DoPostBack("REFRESH"))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            })
        }, initLezioneModal: function (t) {
            handleInputField.init(!0, !0, !1, !1, !0);
            $(".registro-docente-lezione-salva").unbind("click").click(function (i) {
                var u, f, r, e;
                i.preventDefault();
                u = $(this).data("action");
                u !== undefined && (f = $(this).attr("href") + "?Action=" + u, ApplyWait(), r = $("#form-lezione").serializeObject(), r.fsGuids = $("#fsGuids").val(), t === 0 && (r.fuiRedMasterId = $("#fsOreLez").attr("data-id")), r.txtData = $("#ContentPlaceHolderBody_txtDataSelezionataCAL").val(), r.rcla = t, e = JSON.stringify(r), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: f,
                    data: e,
                    dataType: "json",
                    contentType: "json",
                    async: !0,
                    success: function (i) {
                        var r, u;
                        HideWait();
                        i.errorcode !== "0" ? (HideWait(), SweetMessage.error(i.errormsg)) : (r = $("#modal-stack-" + iStackModal), r.modal("hide"), t === 1 ? RegistroClasse.updatePanel("#rcla-tab-alunno") : (u = JSON.parse(i.json), u.bMove === "1" ? ($(n).removeClass("btn-primary").addClass("btn-info"), $(n).html("<i class='far fa-calendar'><\/i> Lezione<\/button>")) : ($(n).removeClass("btn-info").addClass("btn-primary"), $(n).html("<i class='far fa-calendar-check'><\/i> Lezione<\/button>"))), Notific8.success("Operazione eseguita correttamente."))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $(".registro-docente-lezione-elimina").unbind("click").click(function (t) {
                t.preventDefault();
                var i = $(this).data("action"), r = $(this).attr("href") + "?Action=" + i;
                SweetMessage.confirm("Confermi la cancellazione della lezione individuale?", function () {
                    var t, u;
                    i !== undefined && (t = $("#form-lezione").serializeObject(), t.fuiRedMasterId = $("#fsOreLez").attr("data-id"), u = JSON.stringify(t), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: u,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (HideWait(), t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                $(n).removeClass("btn-primary").addClass("btn-info");
                                $(n).html("<i class='far fa-calendar'><\/i> Lezione<\/button>");
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    }))
                })
            })
        }, initCompleto: function () {
            $(function () {
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body", placement: "bottom"})
            });
            LegendaVoti.init();
            AlunnoScheda.init();
            $(".rdoc_full_alunni .cognome").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).closest("tr").index();
                $(this).closest("tr").hasClass("primary") ? ($(this).closest("tr").removeClass("primary"), $("#ContentPlaceHolderBody_idAluSelected").val("nothing"), $(".rdoc_full_valutazioni tbody tr:eq(" + t + ")").removeClass("primary")) : ($(this).closest("tr").addClass("primary").siblings().removeClass("primary"), $("#ContentPlaceHolderBody_idAluSelected").val($(this).closest("tr").data("aluidarc")), $(".rdoc_full_valutazioni tbody tr:eq(" + t + ")").addClass("primary").siblings().removeClass("primary"))
            });
            $(".rdoc_full_alunni tbody tr").hover(function () {
                $(this).addClass("warning");
                $(".rdoc_full_valutazioni tbody tr:eq(" + $(this).index() + ")").addClass("warning")
            }, function () {
                $(this).removeClass("warning");
                $(".rdoc_full_valutazioni tbody tr:eq(" + $(this).index() + ")").removeClass("warning")
            });
            $(".rdoc_full_valutazioni tbody tr").hover(function () {
                $(this).addClass("warning");
                $(".rdoc_full_alunni tbody tr:eq(" + $(this).index() + ")").addClass("warning")
            }, function () {
                $(this).removeClass("warning");
                $(".rdoc_full_alunni tbody tr:eq(" + $(this).index() + ")").removeClass("warning")
            });
            $(".rdoc_full_valutazioni th").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("data");
                t !== undefined && ($(this).addClass("primary").siblings().removeClass("primary"), $("#ContentPlaceHolderMenu_txtDataSelezionata").val(t))
            });
            $(".note-docente-visualizza").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && $("#fsGuids").val() != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#fsGuids").val() + "|" + $("#ContentPlaceHolderBody_idAluSelected").val(), Ajx.getOpenModal(i, !1))
            })
        }
    }
}(), RegistroVoti = function () {
    function r(n) {
        $(".obi-plus").prop("disabled", n > 9 ? !0 : !1);
        $(".obi-minus").prop("disabled", n < 2 ? !0 : !1)
    }

    function u(n, t) {
        for (var f, e, i, s = parseInt($("#fiColObi").val()), h = 0, o = 0, u = 0, r = 1; r <= s; r++) f = parseVoto($("#fsVotoObi-" + Right("00" + r, 2) + "-" + n).val()), f > 0 && (e = parseInt($("#fiPeso-" + Right("00" + r, 2)).val()), h++, o += f * e / 100, u += e / 100);
        u > 0 ? (i = o / u, $("#fsVoto-" + n).val(i !== parseInt(i) ? i.toFixed(2).substring(0, 4) : i.toFixed(0))) : $("#fsVoto-" + n).val(t)
    }

    function i() {
        $("input[name^='fsVoto-']").each(function () {
            var n = $(this).attr("id").substring(7);
            u(n, $("#fsVotoObi-01-" + n).val())
        })
    }

    var t, n;
    return {
        init: function () {
            $(window).load(function () {
                var i = $("#tabVoti").DataTable({
                    paging: !1,
                    searching: !1,
                    ordering: !1,
                    info: !1,
                    scrollY: "200px",
                    scrollCollapse: !0,
                    bAutoWidth: !1,
                    columnDefs: [{width: "30px", targets: 0}, {width: "250px", targets: 1}, {
                        width: "30px",
                        targets: 2
                    }, {width: "30px", targets: 3}]
                }), n = $("body").height() - $(".dataTables_scrollBody").height(), t = $(window).height() - n - 1;
                $(".dataTables_scrollBody").css("max-height", t + "px");
                i.draw();
                $(window).resize(function () {
                    n = $("body").height() - $(".dataTables_scrollBody").height();
                    t = $(window).height() - n - 1;
                    $(".dataTables_scrollBody").css("max-height", t + "px");
                    i.draw()
                });
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
            });
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=chk]").not(this).prop("checked", this.checked)
            });
            QuadroRiepilogativo.initDettaglioVoti();
            RegistroVoti.initVotiInserimentoModifica("voti")
        }, initVotiInserimentoModifica: function (i) {
            n = i;
            $(".registro-" + i + "-multiplo").unbind("click").click(function (t) {
                var i, r, u, f;
                if (t.preventDefault(), $("td input:checkbox[id^=chk]:checked").length === 0) {
                    SweetMessage.error("Nessun alunno selezionato<br>E' necessario selezionare almeno un alunno.");
                    return
                }
                (n !== "docente" || !bSave || confirm_message("Attenzione! Ci sono dati Modificati che continuando andrebbero persi, se si desidera salvarli cliccare su Ok e poi sul pulsante Salva, per proseguire senza salvare cliccare su Annulla.", !0, !1)) && (i = $(this).data("action"), i != undefined && (r = $(this).attr("href") + "?Action=" + i, r += "&Others=" + $(this).data("others"), u = $("#formMasterRegistroElettronico").serializeObject(), f = JSON.stringify(u), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    async: !0,
                    data: f,
                    datatype: "html",
                    success: function (n) {
                        handleModal.openModal(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                })))
            });
            $(".registro-" + i + "-inserimento").unbind("click").click(function (t) {
                var r, i;
                (t.preventDefault(), n !== "docente" || !bSave || confirm_message("Attenzione! Ci sono dati Modificati che continuando andrebbero persi, se si desidera salvarli cliccare su Ok e poi sul pulsante Salva, per proseguire senza salvare cliccare su Annulla.", !0, !1)) && (r = $(this).data("action"), r != undefined && (i = $(this).attr("href") + "?Action=" + r, i += "&Others=" + $(this).closest("tr").data("others"), n === "docente" && (i += "|" + $("#ContentPlaceHolderBody_txtDataSelezionataCAL").val()), Ajx.getOpenModal(i, !1)))
            });
            $(".registro-" + i + "-modifica").unbind("click").click(function (i) {
                var r, u;
                (i.preventDefault(), n !== "docente" || !bSave || confirm_message("Attenzione! Ci sono dati Modificati che continuando andrebbero persi, se si desidera salvarli cliccare su Ok e poi sul pulsante Salva, per proseguire senza salvare cliccare su Annulla.", !0, !1)) && (t = this, r = $(this).data("action"), r != undefined && (u = $(this).attr("href") + "?Action=" + r, u += "&Others=" + $(this).closest("tr").data("others") + "|" + $(this).data("id"), Ajx.getOpenModal(u, !1)))
            })
        }, initVotiMultiploModal: function () {
            handleInputField.init(!0, !1, !0, !0, !0);
            attachSafariModalFix(document.getElementById("modal-stack-" + iStackModal));
            $("select[name^='fuiObi-']").select2("val", "");
            $("#fdDay_dtp").on("dp.change", function () {
                var n = parseDate($("#fdDay").val());
                if (n !== null) {
                    ApplyWait();
                    var t = $("#form-voti").serializeObject(), i = JSON.stringify(t);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: "../Secret/APP_Ajax_Get.aspx?Action=REGISTRO_VOTI_MULTIPLO_ASSENZE",
                        data: i,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = JSON.parse(n.json);
                                t.forEach(function (n) {
                                    n.fsAssente === "" ? $("#fsAssente-" + n.fuiAluId).html(n.fsAssente) : n.fsAssente === "Assente" ? $("#fsAssente-" + n.fuiAluId).html('<span class="label label-sm label-danger">' + n.fsAssente + "<\/span>") : $("#fsAssente-" + n.fuiAluId).html('<span class="label label-sm label-warning">' + n.fsAssente + "<\/span>")
                                })
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $("#fbMedia").on("switchChange.bootstrapSwitch", function (n, t) {
                t === !1 ? ($("#fiPeso").val("0"), $("#fdcValore").val("0"), $("#fiPeso").attr("readonly", !0)) : ($("#fiPeso").val("100"), $("#fdcValore").val(parseVoto($("#fsVoto").val()).toFixed(2)), $("#fiPeso").attr("readonly", !1))
            });
            $(".obi-plus").unbind("click").click(function (n) {
                n.preventDefault();
                var t = parseInt($("#fiColObi").val());
                t < 10 && (t++, $("#fiColObi").val(t), $(".obi-col-" + t).show(), $("#fuiObi-" + Right("00" + t, 2)).attr("disabled", !1));
                r(t);
                i()
            });
            $(".obi-minus").unbind("click").click(function (n) {
                n.preventDefault();
                var t = parseInt($("#fiColObi").val());
                t > 1 && ($("#fuiObi-" + Right("00" + t, 2)).attr("disabled", !0), $(".obi-col-" + t).hide(), t--, $("#fiColObi").val(t));
                r(t);
                i()
            });
            $(".registro-voti-commenti").unbind("click").click(function (n) {
                n.preventDefault();
                var i = this, t = $(this).data("id");
                bootbox.dialog({
                    title: "Commenti al voto per l'alunno " + $(this).data("nome"),
                    message: '<div class="bootstrap row"><div class="col-md-12"><div class="form-group"><label for="fsCommPrv" class="control-label">Commento privato<\/label><textarea class="form-control" name="CommPrv" id="CommPrv" rows="3" maxlength="256">' + $("#fsCommPrv-" + t).val() + '<\/textarea><\/div><\/div><div class="col-md-12"><div class="form-group"><label for="fsCommPrv" class="control-label">Commento pubblico<\/label><textarea class="form-control" name="CommPub" id="CommPub" rows="3" maxlength="256">' + $("#fsCommPub-" + t).val() + "<\/textarea><\/div><\/div><\/div>",
                    closeButton: !0,
                    onEscape: !0,
                    buttons: {
                        confirm: {
                            label: "Ok", className: "btn-success", callback: function () {
                                $("#fsCommPrv-" + t).val($("#CommPrv").val().trim());
                                $("#fsCommPub-" + t).val($("#CommPub").val().trim());
                                $("#CommPrv").val().trim() === "" && $("#CommPub").val().trim() === "" ? $(i).html('<i class="far fa-comment"><\/i>') : $(i).html('<i class="fa fa-commenting"><\/i>')
                            }
                        }, cancel: {label: "Annulla", className: "btn-danger"}
                    }
                })
            });
            $(document).on("hidden.bs.modal", ".bootbox.modal", function () {
                $("body").addClass("modal-open")
            });
            $(".voto-obiettivo").on("input", function () {
                var n = $(this).attr("id").substring(13);
                u(n, $(this).val())
            });
            $(".peso-obiettivo").on("input", function () {
                i()
            });
            $(".registro-voti-multiplo-salva").unbind("click").click(function (n) {
                var i, t, r;
                if (n.preventDefault(), ApplyWait(), i = $("#fsTipo").val() === "O" ? "#fdOrali" : "#fdScritti", t = $("#form-voti"), t.length !== 0 && (t.removeData("validator"), !t.validate({
                    ignore: [],
                    rules: {fdDay: {greaterOrEqualThanDate: i}},
                    messages: {fdDay: {greaterOrEqualThanDate: $.validator.format("La data deve essere maggiore o uguale al " + $(i).val())}}
                }).form())) {
                    HideWait();
                    return
                }
                if (r = $(this).data("action"), r !== undefined) {
                    var u = $(this).attr("href") + "?Action=" + r, f = t.serializeObject(), e = JSON.stringify(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                Notific8.success("Operazione eseguita correttamente.");
                                DoPostBack("REFRESH")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initVotoModal: function () {
            handleInputField.init(!0, !1, !0, !0, !0);
            attachSafariModalFix(document.getElementById("modal-stack-" + iStackModal));
            var i = 1e4;
            $("#fsVoto, #fiPeso").on("input", function () {
                var n = parseVoto($("#fsVoto").val()), t = parseInt($("#fiPeso").val());
                t === 0 && (n = 0);
                $("#fdcValore").val(n.toFixed(2))
            });
            $("#fbMedia").on("switchChange.bootstrapSwitch", function (n, t) {
                t === !1 ? ($("#fiPeso").val("0"), $("#fdcValore").val("0"), $("#fiPeso").attr("readonly", !0)) : ($("#fiPeso").val("100"), $("#fdcValore").val(parseVoto($("#fsVoto").val()).toFixed(2)), $("#fiPeso").attr("readonly", !1))
            });
            $(".registro-voti-obiettivo-inserimento").unbind("click").click(function (n) {
                var t, r;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (r = $(this).attr("href") + "?Action=" + t, r += "&Others=" + $(this).data("others"), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: r,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        i++;
                        n = n.replaceAll("##ID##", "-" + i);
                        $("#tabObiettivi").find("tbody").append(n);
                        $("#fuiObi-" + i).select2();
                        $("#fuiObi-" + i).select2("val", "");
                        $("#fuiObi-" + i).select2("focus")
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("#tabObiettivi").on("click", ".registro-voti-obiettivo-elimina", function (n) {
                n.preventDefault();
                $(this).closest("tr").remove()
            });
            $(".registro-voti-obiettivo-media").unbind("click").click(function (n) {
                var t;
                n.preventDefault();
                var r = 0, u = 0, i = 0;
                $("input[name^='fsVoto-']").each(function () {
                    var n = parseVoto($(this).val()), t = $("#" + $(this).attr("id").replace("fsVoto", "fiPeso")).val();
                    n > 0 && (r++, u += n * t / 100, i += t / 100)
                });
                i > 0 ? (t = u / i, t = t !== parseInt(t) ? t.toFixed(2).substring(0, 4) : t.toFixed(0), $("#fsVoto").val(t), $("#fdcValore").val(parseVoto(t).toFixed(2))) : r > 0 && ($("#fsVoto").val(""), $("#fdcValore").val("0"))
            });
            $(".registro-voti-singolo-salva").unbind("click").click(function (n) {
                var i, t, r;
                if (n.preventDefault(), ApplyWait(), i = $("#fsTipo").val() === "O" ? "#fdOrali" : "#fdScritti", t = $("#form-voto"), t.length !== 0 && (t.removeData("validator"), !t.validate({
                    ignore: [],
                    rules: {
                        fsVoto: {require_from_group: [1, ".group-voto"]},
                        fsCommPrv: {require_from_group: [1, ".group-voto"]},
                        fsCommPub: {require_from_group: [1, ".group-voto"]},
                        fdDay: {greaterOrEqualThanDate: i}
                    },
                    messages: {
                        fsVoto: {require_from_group: $.validator.format("Inserisci almeno il voto o un commento.")},
                        fsCommPrv: {require_from_group: $.validator.format("Inserisci almeno il voto o un commento.")},
                        fsCommPub: {require_from_group: $.validator.format("Inserisci almeno il voto o un commento.")},
                        fdDay: {greaterOrEqualThanDate: $.validator.format("La data deve essere maggiore o uguale al " + $(i).val())}
                    },
                    groups: {voto: "fsVoto fsCommPrv fsCommPub"}
                }).form())) {
                    HideWait();
                    return
                }
                if (r = $(this).data("action"), r !== undefined) {
                    var e = $(this).attr("href") + "?Action=" + r, u = t.serializeObject(), f = JSON.stringify(u);
                    console.log(u);
                    console.log(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: e,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                Notific8.success("Operazione eseguita correttamente.");
                                DoPostBack("REFRESH")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".registro-voti-singolo-elimina").unbind("click").click(function (i) {
                i.preventDefault();
                var r = $(this).data("action"), u = $(this).attr("href"),
                    f = $(this).data("others") + "|" + $("#fiVotoId").val();
                SweetMessage.confirm("Confermi la cancellazione del voto?", function () {
                    if (r != undefined) {
                        var i = u + "?Action=" + r;
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: i,
                            data: f,
                            async: !0,
                            datatype: "json",
                            contentType: "json",
                            success: function (i) {
                                if (i.errorcode !== "0") SweetMessage.error(i.errormsg); else {
                                    var r = $("#modal-stack-" + iStackModal);
                                    r.modal("hide");
                                    n === "docente" ? $(t).remove() : $(t).closest("div").remove();
                                    Notific8.success("Voto eliminato correttamente.")
                                }
                            },
                            error: function (n, t, i) {
                                SweetMessage.error(i.toString())
                            }
                        })
                    }
                })
            })
        }
    }
}(), CertificazioneCompetenze = function () {
    return {
        initProposti: function () {
            QuadroRiepilogativo.init();
            $(window).load(function () {
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
            });
            $(".scrutini-certificazione-competenze-alunno").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            })
        }, initDefinitivi: function (n) {
            CertificazioneCompetenze.initProposti();
            n && $(window).load(function () {
                CertificazioneCompetenze.copiaVotiProposti(!1)
            });
            $("#ContentPlaceHolderMenu_ButtonStampa").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.fuiClaId = $("#fuiClaId").val(), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
            })
        }, initPropostiModal: function () {
            handleInputField.init(!1, !1, !1, !1, !0);
            $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"});
            $(".scrutini-certificazione-competenze-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-competenze"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                Notific8.success("Scheda salvata.");
                                ApplyWait();
                                DoPostBack("REFRESH")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, copiaVotiProposti: function (n) {
            var t = "";
            t = n ? "Copiare i voti proposti sui voti definitivi?<br><br><b>Attenzione, eventuali dati giÃ  presenti verranno sovrascritti.<\/b>" : "E' la prima volta che si accede alla funzione per questa classe.<br><br>Copiare i voti proposti sui voti definitivi?";
            SweetMessage.confirm(t, function () {
                ApplyWait();
                DoPostBack("COPIA_VP")
            })
        }
    }
}(), Family = function () {
    return {
        initCurriculum: function () {
            $(".family-curriculum-asl").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_txtAluSelected").val() + "|" + $(this).data("others")), Ajx.getOpenModal(i, !1))
            })
        }, initPermessi: function () {
            $(document).on("click", ".panel-heading span.collapsible", function () {
                var n = $(this);
                n.hasClass("panel-collapsed") ? (n.parents(".panel").find(".table-responsive").slideDown(), n.parents(".panel").find(".panel-body").slideDown(), n.removeClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-down").addClass("fa fa-chevron-up")) : (n.parents(".panel").find(".table-responsive").slideUp(), n.parents(".panel").find(".panel-body").slideUp(), n.addClass("panel-collapsed"), n.find("i").removeClass("fa-chevron-up").addClass("fa-chevron-down"))
            });
            $("#btnNewPermesso").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_txtAluSelected").val()), Ajx.getOpenModal(i, !0))
            });
            $("#ContentPlaceHolderBody_divContent").on("click", ".permessi-autorizza-command", function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href"), r = $(this).data("others");
                swal({
                    title: "Autorizza il permesso",
                    text: "Inserisci il PIN",
                    type: "question",
                    input: "password",
                    inputAttributes: {autocapitalize: "off", autocorrect: "off"},
                    showCancelButton: !0,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;Conferma",
                    cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;Annulla",
                    preConfirm: function (n) {
                        return new Promise(function (t, i) {
                            n.trim() === "" ? i("Inserire un PIN.") : t()
                        })
                    }
                }).then(function (n) {
                    var u, f;
                    t != undefined && (u = i + "?Action=" + t, u += "&Others=" + r, f = '{fsPin:"' + Base64.encode(n) + '"}', ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (__doPostBack("FAMILY", "PERMESSI"), Notific8.success("Permesso autorizzato."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                }).done()
            });
            $("#ContentPlaceHolderBody_divContent").on("click", ".family-permessi-modifica", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_txtAluSelected").val() + "|" + $(this).data("others")), Ajx.getOpenModal(i, !1))
            });
            $("#ContentPlaceHolderBody_divContent").on("click", ".family-permessi-elimina", function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                i += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_txtAluSelected").val() + "|" + $(this).data("others"));
                SweetMessage.confirm("Vuoi cancellare il permesso autorizzato?", function () {
                    t !== undefined && (ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (DoPostBackFamily("FAMILY", "PERMESSI"), Notific8.success("Permesso eliminato correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                })
            })
        }, initPermessiModal: function () {
            handleInputField.init(!0, !0, !1, !1, !0, !1);
            $(".family-permessi-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-permesso"), t.length === 0 || t.validate().form()) && (i = $(this).data("action"), i !== undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                DoPostBackFamily("FAMILY", "PERMESSI");
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initPagella: function () {
            $(window).load(function () {
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
            });
            $(".family-pagella-scheda-carenze").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $(".family-pagella-invio").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href"), r = $(this).data("others");
                swal({
                    title: "Richiesta Pagella",
                    text: "Inserisci l'indirizzo email dove vuoi che venga inviata la Pagella",
                    type: "question",
                    input: "email",
                    inputAttributes: {autocapitalize: "off", autocorrect: "off"},
                    showCancelButton: !0,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;Conferma",
                    cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;Annulla",
                    inputValidator: function (n) {
                        return new Promise(function (t, i) {
                            /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/.test(n) ? t() : i("Inserire un indirizzo email valido.")
                        })
                    }
                }).then(function (n) {
                    var u, f;
                    t != undefined && (u = i + "?Action=" + t, u += "&Others=" + r, f = '{fsEmail:"' + Base64.encode(n) + '"}', ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : SweetMessage.success("Richiesta inserita correttamente.<br>Il documento richiesto verrÃ  inviato dalla Segreteria Scolastica appena possibile.")
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                }).done()
            });
            $(".export-family-certificato-competenze").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.fsOthers = $(this).data("others"), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
            })
        }, initPagellaSchedaCarenzeModal: function () {
            $("#modal-stack-" + iStackModal + " .modal-body").css("overflow-y", "auto");
            $("#modal-stack-" + iStackModal + " .modal-body").css("max-height", $(window).height() * .75);
            $(".export-family-pagella-scheda-carenze").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (r = $(this).attr("href") + "?Action=" + t, i = {}, i.carenzaId = $("#carenzaId").val(), u = JSON.stringify(i), DownloadFileConHandler.exportStampa(r, u))
            })
        }, initRegistroDocente: function () {
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"})
            });
            $('a[data-toggle="tab"]').on("shown.bs.tab", function (n) {
                var t = $($(n.target).attr("href")), i, r;
                t.html() === "" && (i = t.data("action"), i !== undefined && (r = t.attr("href") + "?Action=" + i, r += "&Others=" + Base64.encode($("#ContentPlaceHolderBody_txtAluSelected").val() + "|" + $("#ContentPlaceHolderMenu_ddlFT").val()), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: r,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        t.html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                })))
            })
        }, initRegistroDocenteElenco: function () {
            $(".family-registro-docenti-voto-obiettivi").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).closest("tr").data("others").split("|")[0], Ajx.getOpenModal(i, !1))
            });
            $(".family-registro-docenti-voto-vista").unbind("click").click(function (n) {
                n.preventDefault();
                var r = $(this).closest("td"), t = $(this).data("action"), u = $(this).attr("href"),
                    f = $(this).closest("tr").data("others").split("|")[0], i = {};
                i = $(this).closest("tr").data("others").split("|")[1] === "2" ? {
                    text: "Confermi la presa visione del voto?",
                    type: "question",
                    showCancelButton: !0,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;SÃ¬",
                    cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;No"
                } : {
                    title: "Presa visione del voto",
                    text: "Inserisci il PIN",
                    type: "question",
                    input: "password",
                    inputAttributes: {autocapitalize: "off", autocorrect: "off"},
                    showCancelButton: !0,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "<i class='far fa-thumbs-up'><\/i>&nbsp;Conferma",
                    cancelButtonText: "<i class='fa fa-times'><\/i>&nbsp;Annulla",
                    preConfirm: function (n) {
                        return new Promise(function (t, i) {
                            n.trim() === "" ? i("Inserire un PIN.") : t()
                        })
                    }
                };
                swal(i).then(function (n) {
                    var i, e;
                    t != undefined && (i = u + "?Action=" + t, i += "&Others=" + f, e = '{fsPin:"' + Base64.encode(n.toString()) + '"}', ApplyWait(), $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        data: e,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            HideWait();
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (r.html(n.json), Notific8.success("Presa visione registrata con successo."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error(i.toString())
                        }
                    }))
                }).done()
            })
        }, initRegistroDocenteGriglia: function () {
            $('[data-toggle="tooltip"]').tooltip({html: !0, container: "body"});
            $(".hScroter1").scroll(function () {
                $(".hScroter2").scrollLeft($(".hScroter1").scrollLeft())
            });
            $(".hScroter2").scroll(function () {
                $(".hScroter1").scrollLeft($(".hScroter2").scrollLeft())
            })
        }, initMateriale: function () {
            $("#ddlDocente").unbind("change").change(function () {
                var t = $(this).data("action"), n;
                t !== undefined && (n = $(this).attr("href") + "?Action=" + t, n += "&Others=" + $("#ddlDocente").val() + "|" + $(this).data("id"), $("#container-folder").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: n,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        $("#container-folder").html(n)
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#container-folder").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                }))
            })
        }, initMaterialeFolder: function () {
            $("#accordion").unbind("show.bs.collapse").on("show.bs.collapse", function (n) {
                var r = $(this).data("action"), t, i;
                r !== undefined && (t = $(n.target), t.html().indexOf("table") === -1 && (i = $(this).attr("href") + "?Action=" + r, i += "&Others=" + $(n.target).data("others"), t.html('<div class="panel-body"><i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span><\/div>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        t.html(n)
                    },
                    error: function (n, i, r) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + r.toString());
                        t.html('<div class="panel-body"><b>Errore durante la chiamata al Server.<b><\/div>')
                    }
                })))
            })
        }, initMaterialeContent: function () {
            DownloadFileConHandler.DownloadFile();
            $(".family-materiale-didattico-testo").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ddlDocente").val() + "|" + $(this).closest("tr").data("others"), Ajx.getOpenModal(i, !1))
            })
        }, initMaterialeContentTesto: function () {
            $("#fsTesto").val(Base64.decode($("#fsTesto").val()));
            $("#fsTesto").summernote({height: 500, lang: "it-IT", toolbar: []});
            $("#fsTesto").summernote("disable")
        }, initColloqui: function () {
            handleInputField.init(!1, !1, !1, !0, !1)
        }, initColloquiSalva: function () {
            var n = $("#formMasterRegistroElettronico").serializeObject(), t = JSON.stringify(n);
            ApplyWait();
            $.ajax({
                type: "POST",
                cache: !1,
                url: "../Secret/APP_Ajax_Post.aspx?Action=COLLOQUI_INSERIMENTO_SALVA",
                data: t,
                dataType: "json",
                contentType: "json",
                async: !0,
                success: function (n) {
                    HideWait();
                    n.errorcode !== "0" ? SweetMessage.errorCallBack(n.errormsg, function () {
                        DoPostBackFamily("FAMILY", "PC")
                    }) : (Notific8.success("Colloqui prenotati correttamente."), DoPostBackFamily("FAMILY", "PC"))
                },
                error: function (n, t, i) {
                    HideWait();
                    SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                }
            })
        }
    }
}(), Orario = function () {
    var n, t = function () {
        var n = $("#content-orari");
        $.ajax({
            type: "GET",
            cache: !1,
            url: "../Secret/APP_Ajax_Get.aspx?Action=ORARIO_LISTA_REFRESH",
            async: !0,
            datatype: "html",
            success: function (t) {
                n.html(t)
            },
            error: function (n, t, i) {
                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
            }
        })
    };
    return {
        init: function () {
            $("#btnNewOrario").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    dataType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($("#collapseDettaglio").html(n.html), $(".collapse").collapse("show"), $("#collapseElenco").collapse("hide"))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("#tabOrario").on("shown.bs.tab", function (n) {
                var t = $($(n.target).attr("href")), i, r;
                t.html() === "" && (i = t.data("action"), i !== undefined && (r = t.attr("href") + "?Action=" + i, ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: r,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        t.html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                })))
            });
            $("#content-orari").on("click", ".orario-classi-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t + "&Others=" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $("#content-orari").on("click", ".orario-modifica-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t + "&Others=" + $(this).closest("tr").data("id"), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    dataType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($("#collapseDettaglio").html(n.html), $(".collapse").collapse("show"), $("#collapseElenco").collapse("hide"))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("#content-orari").on("click", ".orario-duplica-questo-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t + "&Others=" + $(this).closest("tr").data("id"), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    dataType: "json",
                    async: !0,
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : ($("#collapseDettaglio").html(n.html), $(".collapse").collapse("show"), $("#collapseElenco").collapse("hide"))
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            });
            $("#content-orari").on("click", ".orario-duplica-altri-command", function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t + "&Others=" + $(this).closest("tr").data("id"), Ajx.getOpenModal(i, !1))
            });
            $("#content-orari").on("click", ".orario-elimina-command", function (n) {
                var i, u, r, f;
                n.preventDefault();
                i = $(this).data("action");
                i != undefined && (u = $(this).attr("href") + "?Action=" + i, r = {}, r.fiOrarioId = $(this).closest("tr").data("id"), f = JSON.stringify(r), SweetMessage.confirm("Vuoi cancellare questo orario?", function () {
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: f,
                        async: !0,
                        datatype: "json",
                        contentType: "json",
                        success: function (n) {
                            n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : (t(), Notific8.success("Comunicazione eliminata correttamente."))
                        },
                        error: function (n, t, i) {
                            SweetMessage.error(i.toString())
                        }
                    })
                }))
            })
        }, initOrarioDuplicaAltriModal: function () {
            $("#chkAll").click(function () {
                $("td input:checkbox").not(this).prop("checked", this.checked)
            });
            $(".orario-dupplica-salva").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).data("action"), i = $(this).attr("href") + "?Action=" + t;
                SweetMessage.confirm("Vuoi copiare l'orario nei plessi selezionati?", function () {
                    if (t != undefined) {
                        var n = $("#form-orario").serializeObject(), r = JSON.stringify(n);
                        $.ajax({
                            type: "POST",
                            cache: !1,
                            url: i,
                            data: r,
                            dataType: "json",
                            contentType: "json",
                            async: !0,
                            success: function (n) {
                                if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                    var t = $("#modal-stack-" + iStackModal);
                                    t.modal("hide");
                                    Notific8.success("Operazione eseguita correttamente.")
                                }
                            },
                            error: function (n, t, i) {
                                SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                            }
                        })
                    }
                })
            })
        }, initDettaglio: function () {
            handleInputField.init(!0, !0, !1, !0, !1, !1);
            $(".orario-clear-riga").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).closest("tr").data("row");
                $("input[name^=ftOraIni" + t).each(function () {
                    $(this).val("00:00")
                });
                $("input[name^=ftOraEnd" + t).each(function () {
                    $(this).val("00:00")
                });
                $("input[name^=fsFlag" + t).each(function () {
                    $(this).prop("checked", !0)
                })
            });
            $(".orario-copy-right").unbind("click").click(function (n) {
                n.preventDefault();
                var t = $(this).closest("tr").data("row");
                $("input[name^=ftOraIni" + t).each(function () {
                    var n = $(this).attr("id").substr($(this).attr("id").length - 3);
                    $("#ftOraIni" + n).val($("#ftOraIni" + t + "1").val());
                    $("#ftOraEnd" + n).val($("#ftOraEnd" + t + "1").val());
                    $("#fsFlag" + n).prop("checked", $("#fsFlag" + t + "1").prop("checked"))
                })
            });
            $(".orario-flag-diurno").unbind("click").click(function () {
                var n = $(this).attr("id").substr($(this).attr("id").length - 3),
                    t = $(this).attr("id").substr($(this).attr("id").length - 1);
                $("input[name^=fsFlag][name$=" + t + "]").each(function () {
                    $(this).attr("id").substr($(this).attr("id").length - 3) !== n && $(this).prop("checked", $(this).attr("id").substr($(this).attr("id").length - 3) < n ? !0 : !1)
                })
            });
            $(".orario-salva").unbind("click").click(function (n) {
                var i, r;
                if (n.preventDefault(), i = $("#formMasterRegistroElettronico"), i.length !== 0 && !i.validate({
                    ignore: [],
                    rules: {fdOrarioIni: {greaterOrEqualThanDate: "#fdAsIni"}},
                    messages: {fdOrarioIni: {greaterOrEqualThanDate: $.validator.format("La data deve essere maggiore o uguale al " + $("#fdAsIni").val())}}
                }).form()) {
                    HideWait();
                    return
                }
                if (r = $(this).data("action"), r != undefined) {
                    var u = $(this).attr("href") + "?Action=" + r, f = i.serializeObject(), e = JSON.stringify(f);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                t();
                                var i = JSON.parse(n.json);
                                $("#fiOrarioId").val(i.orarioId);
                                i.isDefault === "1" && $("#fbDefault").bootstrapSwitch("disabled", !0);
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initClassiModal: function () {
            $("#chkAll").click(function () {
                $("td input:checkbox[id^=fuiClaGrp]").not(this).prop("checked", this.checked)
            });
            $(".orario-classi-salva").unbind("click").click(function (n) {
                var i;
                if (n.preventDefault(), i = $(this).data("action"), i != undefined) {
                    var r = $(this).attr("href") + "?Action=" + i, u = $("#form-classi").serializeObject(),
                        f = JSON.stringify(u);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                t();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            })
        }, initSelectDocenti: function () {
            $("#ddlDocente").select2({placeholder: "Seleziona un docente  ..."});
            $("#ddlDocente").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ddlDocente").val(), $("#content-periodi").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        $("#content-periodi").html(n)
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#content-periodi").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                }))
            })
        }, initDocentiPeriodi: function () {
            $("#ddlPeriodo").select2({placeholder: "Seleziona un periodo  ..."});
            $("#btnNewPeriodo").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ddlDocente").val(), Ajx.getOpenModal(i, !0))
            });
            $("#btnUpdPeriodo").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ddlPeriodo").val(), Ajx.getOpenModal(i, !0))
            });
            $("#ddlPeriodo").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                $("#btnUpdPeriodo").removeClass("hidden");
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $("#ddlPeriodo").val(), ApplyWait(), $("#content-lezioni").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        HideWait();
                        $("#content-lezioni").html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#content-lezioni").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                }))
            })
        }, initOrarioDocentiModal: function () {
            handleInputField.init(!0, !1, !1, !1, !0, !1);
            $("#idPeriodo").select2({placeholder: "Seleziona un periodo se si vogliono copiare le lezioni ..."});
            $(".orario-docenti-salva").unbind("click").click(function (n) {
                var t, i;
                if ((n.preventDefault(), t = $("#form-oradoc"), t.length === 0 || t.validate({
                    ignore: [],
                    rules: {fdOraIni: {greaterOrEqualThanDate: "#fdMin"}},
                    messages: {fdOraIni: {greaterOrEqualThanDate: $.validator.format("La data deve essere maggiore o uguale al " + $("#fdMin").val())}}
                }).form()) && (i = $(this).data("action"), i != undefined)) {
                    var r = $(this).attr("href") + "?Action=" + i, u = t.serializeObject(), f = JSON.stringify(u);
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: f,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                $("#ddlDocente").change();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".orario-docenti-elimina").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, SweetMessage.confirm("Vuoi cancellare questo orario?", function () {
                    var n = $("#form-oradoc").serializeObject(), t = JSON.stringify(n);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: i,
                        data: t,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (n) {
                            if (HideWait(), n.errorcode !== "0") SweetMessage.error(n.errormsg); else {
                                var t = $("#modal-stack-" + iStackModal);
                                t.modal("hide");
                                $("#ddlDocente").change();
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }))
            })
        }, initOrarioDocentiLezioni: function () {
            $(".orario-docenti-lezione").unbind("click").click(function (t) {
                var i, r;
                t.preventDefault();
                n = this;
                i = $(this).data("action");
                i != undefined && (r = $(this).attr("href") + "?Action=" + i, r += "&Others=" + $("#ddlPeriodo").val() + "|" + $(this).data("others"), Ajx.getOpenModal(r, !0))
            })
        }, initOrarioDocentiLezioneModal: function () {
            $("#fsClaMat").select2({placeholder: "Seleziona la classe/materia o un gruppo ..."});
            $("#fuiAlunniIds").select2({placeholder: "Seleziona uno o piÃ¹ alunni se non Ã¨ tutta la classe/gruppo ..."});
            $("#fsClaMat").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t != undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + $(this).val() + "|" + $("#ddlDocente").val(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        $("#fuiAlunniIds").select2("destroy");
                        $("#content-alunni").html(n);
                        $("#fuiAlunniIds").select2({placeholder: "Seleziona uno o piÃ¹ alunni se non Ã¨ tutta la classe/gruppo ..."})
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#fuiAlunniIds").select2("destroy");
                        $("#content-alunni").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                }))
            });
            $(".orario-docenti-lezione-salva").unbind("click").click(function (t) {
                var r, u, i, f, e;
                if (t.preventDefault(), r = $(this).data("action"), r != undefined) {
                    if (u = $(this).attr("href") + "?Action=" + r, i = $("#form-oralez"), i.length !== 0 && !i.validate({ignore: []}).form()) return;
                    f = i.serializeObject();
                    e = JSON.stringify(f);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: u,
                        data: e,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            var u, i, r;
                            HideWait();
                            t.errorcode !== "0" ? SweetMessage.error(t.errormsg) : (u = $("#modal-stack-" + iStackModal), u.modal("hide"), i = JSON.parse(t.json), r = "<label class='label label-sm label-info' style='cursor: pointer;'>" + i.materia + "<\/label>&nbsp;/&nbsp;<label class='label label-sm label-default' style='cursor: pointer;'>" + i.classe + "<\/label>", i.alunni !== "" && (r += "<br><label class='label label-xs label-primary' style='cursor: pointer;'>" + i.alunni + "<\/label>"), $(n).html(r), $("#content-numero").html("Numero lezioni: " + i.numero), Notific8.success("Operazione eseguita correttamente."))
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }
            });
            $(".orario-docenti-lezione-elimina").unbind("click").click(function (t) {
                var i, r;
                t.preventDefault();
                i = $(this).data("action");
                i != undefined && (r = $(this).attr("href") + "?Action=" + i, SweetMessage.confirm("Vuoi cancellare questa lezione dall'orario?", function () {
                    var t = $("#form-oralez").serializeObject(), i = JSON.stringify(t);
                    ApplyWait();
                    $.ajax({
                        type: "POST",
                        cache: !1,
                        url: r,
                        data: i,
                        dataType: "json",
                        contentType: "json",
                        async: !0,
                        success: function (t) {
                            if (HideWait(), t.errorcode !== "0") SweetMessage.error(t.errormsg); else {
                                var i = $("#modal-stack-" + iStackModal);
                                i.modal("hide");
                                $(n).html("<i class='fa fa-plus'><\/i>");
                                $("#content-numero").html("Numero lezioni: " + t.json);
                                Notific8.success("Operazione eseguita correttamente.")
                            }
                        },
                        error: function (n, t, i) {
                            HideWait();
                            SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                        }
                    })
                }))
            })
        }
    }
}(), GeniusBoard = function () {
    return {
        init: function () {
            $("#kkAccesso").unbind("click").click(function (n) {
                var t, r, i, u;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (r = $(this).attr("data-href") + "?Action=" + t, ApplyWait(), i = {}, i.classe = $("#ContentPlaceHolderMenu_ddlClassi").val(), u = JSON.stringify(i), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: r,
                    async: !0,
                    data: u,
                    success: function (n) {
                        HideWait();
                        window.open(n.result.link, "_blank")
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                }))
            });
            $("#kkImporta").unbind("click").click(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).attr("data-action");
                t !== undefined && (i = $(this).attr("data-href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#ContentPlaceHolderMenu_ddlClassi").val() + "|" + $("#ContentPlaceHolderMenu_ddlFT").val()), ApplyWait(), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    dataType: "json",
                    success: function (n) {
                        HideWait();
                        n.errorcode !== "0" ? SweetMessage.error(n.errormsg) : handleModal.openModal(n.html)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error(i.toString())
                    }
                }))
            })
        }, initImportazioneModal: function () {
            $("#fiTest").select2("destroy");
            $("#fiTest").select2({placeholder: "Seleziona un test/quiz per visualizzare i voti..."});
            $("#fiTest").unbind("change").change(function (n) {
                var t, i;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).data("href") + "?Action=" + t, i += "&Others=" + $("#fuiGuids").val() + "|" + $(this).val(), $("#content-impari-voti").html('<i class="fas fa-sync fa-spin fa-2x fa-fw"><\/i><span>Lettura dati...<\/span>'), $.ajax({
                    type: "GET",
                    cache: !1,
                    url: i,
                    async: !0,
                    datatype: "html",
                    success: function (n) {
                        $("#content-impari-voti").html(n)
                    },
                    error: function (n, t, i) {
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString());
                        $("#content-impari-voti").html("<b>Errore durante la chiamata al Server.<b>")
                    }
                }))
            })
        }, initElencoVoti: function () {
            $("#chkStudenti").click(function () {
                $("td input:checkbox[id^=fuiStudente]").not(this).prop("checked", this.checked);
                $(".geniusboard-impari-importa").prop("disabled", this.checked === !1)
            });
            $("td input:checkbox[id^=fuiStudente]").click(function () {
                $("#chkStudenti").prop("checked", !1);
                $("td input:checkbox[id^=fuiStudente]:checked").length === 0 ? $(".geniusboard-impari-importa").prop("disabled", !0) : $(".geniusboard-impari-importa").prop("disabled", !1)
            });
            $(".geniusboard-impari-importa").unbind("click").click(function (n) {
                var t, i, r, u;
                n.preventDefault();
                t = $(this).data("action");
                t !== undefined && (i = $(this).attr("href") + "?Action=" + t, i += "&Others=" + Base64.encode($("#fiTest").val() + "|" + $("#fiTest").select2("data").text), r = $("#form-impari").serializeObject(), u = JSON.stringify(r), ApplyWait(), $.ajax({
                    type: "POST",
                    cache: !1,
                    url: i,
                    async: !0,
                    data: u,
                    datatype: "html",
                    success: function (n) {
                        $("#fiTest").select2("destroy");
                        HideWait();
                        var t = $("#modal-stack-" + iStackModal);
                        t.html(n)
                    },
                    error: function (n, t, i) {
                        HideWait();
                        SweetMessage.error("Errore durante la chiamata al Server: " + i.toString())
                    }
                }))
            })
        }
    }
}();