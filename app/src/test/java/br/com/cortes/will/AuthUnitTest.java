package br.com.cortes.will;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cortes.will.request.Authentication;

public class AuthUnitTest {

    private Authentication mAuth;
    private final int HASH_SIZE = 32;

    @Before
    public void beforeTest() {
        mAuth = new Authentication();
    }

    @Test
    public void validateHashAuthentication() {
        Assert.assertNotNull(mAuth.getmHash());
        Assert.assertEquals(mAuth.getmHash().length(), HASH_SIZE);
    }
}
