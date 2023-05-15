package com.udacity.jwdnd.course1.cloudstorage.apiModel.request;

public class NoteRequest {
    private String noteTitle;
    private String noteDescription;
    private Integer userId;
    private Integer noteId;



    public NoteRequest(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }



    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }
}
