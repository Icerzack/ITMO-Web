function setupCanvas(canvas) {
    let dpr = window.devicePixelRatio || 1;
    let rect = canvas.getBoundingClientRect();
    canvas.width = rect.width * dpr;
    canvas.height = rect.height * dpr;
    canvas.strokeStyle = '#000'
    let ctx = canvas.getContext('2d');
    ctx.scale(dpr, dpr);
    return ctx;
}

let canvas = document.getElementById('area');

let ctx = setupCanvas(canvas)
const shiftX = 200
const shiftY = 200
const scaleX = 1
const scaleY = -1
ctx.transform(scaleX, 0, 0, scaleY, shiftX, shiftY);
ctx.strokeStyle = '#444444';

let axisSize = 175
let arrowSize = 7
function drawAxes() {
    ctx.fillStyle = '#444'
    ctx.beginPath()
    ctx.font = "14px sans-serif";
    ctx.textAlign = 'center'
    ctx.textBaseline = 'middle'

    //Ось Y
    ctx.lineWidth = 1;
    ctx.moveTo(0, -axisSize);
    ctx.lineTo(0, axisSize);

    ctx.moveTo(-arrowSize, axisSize - arrowSize);
    ctx.lineTo(0, axisSize);
    ctx.lineTo(arrowSize, axisSize - arrowSize);

    ctx.scale(1, -1)
    ctx.fillText("Y", 0, -190);
    ctx.scale(1, -1)

    //Ось X
    ctx.moveTo(-axisSize, 0);
    ctx.lineTo(axisSize, 0);

    ctx.moveTo(axisSize - arrowSize, -arrowSize);
    ctx.lineTo(axisSize, 0);
    ctx.lineTo(axisSize - arrowSize, arrowSize);

    ctx.fillText("X", 190, 1);
    ctx.stroke();
}
function drawArea(radius) {
    ctx.fillStyle = '#8ea1f0';

    //rectangle
    ctx.fillRect(0, 0, -radius/2, radius)

    //triangle
    ctx.beginPath()
    ctx.moveTo(0, 0)
    ctx.lineTo(-radius/2, 0)
    ctx.lineTo(0, -radius / 2)
    ctx.fill();

    // circle
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.arc(0, 0, radius/2, 0, Math.PI / 2)
    ctx.fill();
}
function drawHit(x, y, doesItHit) {
    ctx.beginPath();
    ctx.arc(x, y, 5, 0, 2 * Math.PI, false);

    ctx.fillStyle = doesItHit ? "#00FF00" : "#FF0000"
    ctx.fill();
    ctx.lineWidth = 0.5

    ctx.strokeStyle = '#000'
    ctx.stroke()
}
let radius = 150;
drawArea(radius)
drawAxes()

canvas.addEventListener('click', function(event) {
    let canvasX = scaleX * (event.offsetX - shiftX);
    let canvasY = scaleY * (event.offsetY - shiftY);

    let r = document.getElementById("coordinatesForm:r").value;
    let ratio = r / radius
    let x = canvasX * ratio;
    let y = canvasY * ratio;

    document.getElementById("chart-form:chart-x").value = x.toFixed(2);
    document.getElementById("chart-form:chart-y").value = y.toFixed(2);
    document.getElementById("chart-form:chart-r").value = r.toString();
    document.getElementById("chart-form:submit").click();
})
setInterval( function () {
    let rField = document.getElementById("coordinatesForm:r");

    kek = (e) => {
        console.log(e.target.value);
        if(e.target.value<=3.9999 && e.target.value>=1.0001){
            console.log(e)
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.clearRect(0, 0, -canvas.width, canvas.height);
            ctx.clearRect(0, 0, -canvas.width, -canvas.height);
            ctx.clearRect(0, 0, canvas.width, -canvas.height);
            radius = e.target.value * 75;
            drawArea(radius)
            drawAxes()
            document.getElementById("chart-form:refresh").click();
        }
    };
        rField.addEventListener("input", kek, false);
        rField.addEventListener("keydown", kek);
},1000);

function addHits(hits) {
    for (let hit of hits) {
        let ratio = radius / hit.r;

        drawHit(hit.x * ratio, hit.y * ratio, hit.doesHit)
    }
}