const mealAjaxUrl = "rest/profile/meals/";
let form = $('#mealDetailsForm');

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

function save() {
    console.log('save in meals');
    console.log('data: ' + form.serialize());
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: form.serialize()
    })
        .done(function () {
            $("#mealFormModal").modal("hide");
            updateTable();
            successNoty("Meal Saved");
        })
}

function cleanFilter() {
    console.log('clean filter');
    // $("#mealFilter").trigger("reset");
    console.log("filter element: ", document.getElementById("mealFilter")[2]);
    $("#mealFilter")[2].reset();
    $.get(mealAjaxUrl, updateTable)
    console.log('after filtering')
}

function deleteMeal(id) {
    $.ajax({
        url: ctx.ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("Deleted");
    });
}

function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    $(".delete").click(function () {
        if (confirm('Are you sure?')) {
            deleteMeal($(this).closest('tr').attr("id"));
        }
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    console.log("add meal");
    form.find(":input").val("");
    // console.log($("mealFormModal"))
    $('#mealFormModal').modal();
    console.log("2")

}

function updateTable() {
    $.get(ctx.ajaxUrl, function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}