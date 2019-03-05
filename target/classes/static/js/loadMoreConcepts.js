var conceptPage = 1;

function loadMoreConcepts() {
    var urlPage = "/table-concept?page=" + conceptPage;
    
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#moreConcepts").append(data);
        conceptPage++;
        
        if (indexConcept == conceptPage){
        	$("#buttonMoreConcepts").hide();
        }
    })
}