package musicplayer.validator;

import musicplayer.entity.Song;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class songValidator implements Validator{
	
	 	@Override
	    public boolean supports(Class<?> aClass) {
	        return Song.class.equals(aClass);
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	        Song song = (Song) o;

			MultipartFile multipartFile = song.getSongFile();
			if (!this.isSupportedContentType(multipartFile.getContentType())) {
				errors.rejectValue("songFile", "Invalid.Song.songFile");
			}
	    }
		private boolean isSupportedContentType(String contentType) {
			return contentType.equals("audio/mpeg");
		}

}
